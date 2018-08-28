(ns scramblies.ui.core
  "Main entry point for the frontend"
  (:require [reagent.core :as r]
            [scramblies.ui.components :as scramblies]
            [scramblies.ui.state :as state]))

(defn mount []
  (let [initial-state {::scramblies/haystack "alien technology"
                       ::scramblies/needle "theological"
                       ::state/scramble true}]
    (reset! state/state initial-state)
    (r/render [scramblies/scramblies]
              (js/document.getElementById "scramblies"))))

(mount)
