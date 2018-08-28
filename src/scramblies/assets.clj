(ns scramblies.assets
  (:require [compojure.core :refer :all]
            [clojure.java.io :as io]
            [ring.util.response :as resp]
            [compojure.route :as route]))

(def index
  (-> (resp/response (slurp (io/resource "index.html")))
      (resp/header "Content-Type" "text/html")))

(defroutes assets*
  (GET "/" _ index)
  (route/resources "/" {:root ""}))
