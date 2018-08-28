(ns scramblies.http
  (:require [ring.util.response :as resp]
            [clojure.tools.logging :as log]))

(defn response
  "Applies f to args and turns the result into an HTTP response"
  [f & args]
  (try
    (let [result (pr-str (apply f args))]
      (-> (resp/response result)
          (resp/header "Content-Type" "application/edn")))
    (catch Exception ex
      (log/info "kaboom" ex)
      (-> (resp/response (.getMessage ex))
          (resp/status 500)))))
