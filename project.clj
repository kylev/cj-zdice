(defproject cj-zdice "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [ring/ring "1.2.2"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]
                 [om "0.6.2"]]
  :main cj-zdice.server

  :plugins [[lein-cljsbuild "1.0.3"]]

  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["resources"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/clj" "src/cljs"]
              :compiler {
                :output-to "resources/public/js/main.js"
                :output-dir "resources/public/js/out"
                :optimizations :none
                :source-map true}}]})
