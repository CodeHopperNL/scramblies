(ns scramblies.app
  (:require [ring.middleware.defaults :refer :all]
            [scramblies.api :as api]))

(def app
  (-> api/api*
      (wrap-defaults api-defaults)))
