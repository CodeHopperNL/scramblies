(ns scramblies.api
  (:require [compojure.core :refer :all]
            [scramblies.core :as scramblies]
            [scramblies.http :as http]
            [ring.util.response :as resp]))

(defn scramble-response [str1 str2]
  {::scramble? (scramblies/scramble? str1 str2)})

(defroutes api*
  (context "/api" []
    (GET "/scramble" [a b] (http/response scramble-response a b))))
