(ns scramblies.ui.http
  "Client-server interactions"
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn scramble?
  "Side effects free, testable scramble HTTP response processing logic."
  [{:keys [status body]}]
  (if (= 200 status)
    (case (:scramblies.api/scramble? body)
      nil
      (throw (ex-info "Invalid API response" {::body body
                                              ::status status}))

      ;;else
      (:scramblies.api/scramble? body))

    (throw (ex-info "Invalid API response" {::body body
                                            ::status status}))))

(defn get-scramble!
  "Retrieves the scramble result from the server, or nil in case of errors."
  [str1 str2]
  (go (let [response (<! (http/get "/api/scramble"
                                   {:query-params {:a str1
                                                   :b str2}}))]
        (scramble? response))))
