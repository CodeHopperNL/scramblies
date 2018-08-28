(ns scramblies.ui.components
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :refer [<!]]
            [scramblies.ui.http :as http]
            [scramblies.ui.state :as state]))

;;
;; Utility functions
;;

(defn scramblie-updater
  "Generates a scramblier updater function that fetches the new scramble value from the api"
  [scramblie]
  (fn [e]
    (let [current-value (.-value (.-currentTarget e))]
      (swap! state/state assoc scramblie current-value))
    (go (state/lock!)
        (try
          (let [scramble (<! (http/get-scramble! (::haystack @state/state) (::needle @state/state)))]
            (when (some? scramble)
              (swap! state/state assoc ::state/scramble scramble)))
          (finally
            (state/unlock!))))))

;;
;; React components
;;

(defn input-text [role]
  (let [value (get @state/state role)]
    [:input {:type "text"
             :minLength 50
             :maxLength 50
             :value value
             :onChange (scramblie-updater role)}]))

(defn happy-face []
  [:span {:title "It's a scramble!"} "😍"])

(defn sad-face []
  [:span {:title "It's a sad day for scramblies.."} "😭"])

(defn scramble? []
  [:div.scramble--result (if (true? (::state/scramble @state/state))
                           [happy-face]
                           [sad-face])])

(defn scramblies []
  [:div {:id "scramblies--root"}
   [input-text ::haystack]
   [scramble?]
   [input-text ::needle]])
