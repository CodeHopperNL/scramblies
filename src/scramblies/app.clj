(ns scramblies.app
  (:require [ring.middleware.defaults :refer :all]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [scramblies.api :as api]
            [scramblies.assets :as assets]))

(def app
  (routes
   (-> api/api*
       (wrap-defaults api-defaults))
   (-> assets/assets*
       (wrap-defaults site-defaults))
   (route/not-found "Not found")))
