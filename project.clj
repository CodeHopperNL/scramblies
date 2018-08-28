(defproject scramblies "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/spec.alpha "0.2.168"]
                 [orchestra "2018.08.19-1"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [org.clojure/tools.logging "0.4.1"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.8.1"]
                 [cljs-http "0.1.45"]
                 [org.clojure/core.async "0.4.474"]]
  :plugins [[lein-ring "0.12.4"]
            [lein-figwheel "0.5.13"]]
  :clean-targets [:target-path "out"]
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :figwheel true
                        :compiler {:main "scramblies.ui.core"
                                   :asset-path "build/out"
                                   :output-to "resources/build/scramblies.js"
                                   :output-dir "resources/build/out"}}]}
  :ring {:handler scramblies.app/app}
  :profiles {:dev {:source-paths ["src" "dev"]
                   :dependencies [[ring/ring-jetty-adapter "1.7.0-RC2"]]}})
