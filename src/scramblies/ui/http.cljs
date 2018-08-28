(ns scramblies.ui.http
  "Client-server interactions"
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(defn get-scramble!
  "Retrieves the scramble result from the server, or nil in case of errors."
  [str1 str2]
  (go (let [response (<! (http/get "/api/scramble"
                                   {:query-params {:a str1
                                                   :b str2}}))]
        (if (= 200 (:status response))
          (get-in response [:body :scramblies.api/scramble?])
          (js/console.log "API call error:" (:status response))))))
