(defproject cj-zdice "0.2.0-SNAPSHOT"
  :description "FIXME: write this!"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [ring/ring "1.2.2"]
                 [om "0.6.2"]
                 [compojure "1.1.6"]]

  :min-lein-version "2.1.2"
  :uberjar-name "cj-zdice-standalone.jar"

  :plugins [[lein-cljsbuild "1.0.3"]]

  :hooks [leiningen.cljsbuild]

  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["resources"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/cljs"]
              :compiler {
                :output-to "resources/public/js/main.js"
                :output-dir "resources/public/js/out"
                :optimizations :none
                :pretty-print true
                :source-map "resources/public/js/main.js.map"}}]})
