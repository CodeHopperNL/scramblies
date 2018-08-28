(ns user
  "Useful tooling for dev times"
  (:require [clojure.tools.logging :as log]
            [ring.adapter.jetty :as jetty]
            [scramblies.app :as app])
  (:import [org.eclipse.jetty.server Server]))

(set! *warn-on-reflection* true)

(defonce ^:private jetty-server (atom nil))

(defn- started? [server]
  (or (some? server)))

(defn start! [& {:keys [app port] :or {app #'app/app port 8888}}]
  (pr "app" app)
  (if (started? @jetty-server)
    (log/info "Server already started, skipping")
    (letfn [(start* [_] (jetty/run-jetty app {:port  port
                                              :join? false}))]
      (log/info "Starting server at port" port)
      (swap! jetty-server start*))))

(defn stop! []
  (letfn [(stop* [^Server server]
            (when (some? server)
              (log/info "Stopping server")
              (.stop server))
            nil)]
    (swap! jetty-server stop*)))
