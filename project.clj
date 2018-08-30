(defproject scramblies "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Unlicense"
            :url "https://unlicense.org/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/spec.alpha "0.2.168"]
                 [orchestra "2018.08.19-1"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [org.clojure/tools.logging "0.4.1"]
                 [org.clojure/clojurescript "1.9.946"]
                 [reagent "0.8.1"]
                 [cljs-http "0.1.45"]
                 [doo "0.1.10"]
                 [org.clojure/core.async "0.4.474"]]
  :plugins [[lein-ring "0.12.4"]
            [lein-doo "0.1.10"]
            [lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.13"]]
  :aliases {"start" ["with-profile" "ring" "ring" "server"]}
  :doo {:build "test"
        :alias {:default [:phantom]}}
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :figwheel true
                        :compiler {:main "scramblies.ui.core"
                                   :asset-path "build/dev/out"
                                   :output-to "resources/build/scramblies.js"
                                   :output-dir "resources/build/dev/out"}}
                       {:id "prod"
                        :source-paths ["src"]
                        :jar true
                        :compiler {:main "scramblies.ui.core"
                                   :asset-path "build/prod/out"
                                   :optimizations :advanced
                                   :output-to "resources/build/scramblies.js"
                                   :output-dir "resources/build/prod/out"}}
                       {:id "test"
                        :source-paths ["src" "test" "test-resources"]
                        :compiler {:main "scramblies.ui.test-runner"
                                   :asset-path "build/test/out"
                                   :output-to "test-resources/build/unit-test.js"
                                   :output-dir "test-resources/build/test/out"
                                   :optimizations :whitespace
                                   :pretty-print true}}]}
  :clean-targets ^{:protect false} ["resources/build"
                                    "test-resources/build"
                                    :target-path]
  :ring {:handler scramblies.app/app}
  :profiles {:dev {:source-paths ["src" "dev"]
                   :dependencies [[ring/ring-jetty-adapter "1.7.0-RC2"]]}
             :ring {:prep-tasks ["compile" ["cljsbuild" "once" "prod"]]}
             :uberjar {:prep-tasks ["compile" ["cljsbuild" "once" "prod"]]}})
