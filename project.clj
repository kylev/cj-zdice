(defproject cj-zdice "0.3.0-SNAPSHOT"
  :description "FIXME: write this!"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2760"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [ring/ring "1.3.2"]
                 [environ "1.0.0"]
                 [com.taoensso/carmine "2.9.0" :exclusions [org.clojure/clojure]]
                 [om "0.7.3"]
                 [om-sync "0.1.1"]
                 [compojure "1.3.1"]
                 [enlive "1.1.5"]
                 [fogus/ring-edn "0.2.0"]]

  :min-lein-version "2.1.2"
  :uberjar-name "cj-zdice-standalone.jar"
  :main ^:skip-aot cj-zdice.server

  :plugins [[lein-cljsbuild "1.0.4"]]

  :hooks [leiningen.cljsbuild]

  :source-paths ["src/clj"]
  :resource-paths ["resources"]
  :profiles {:uberjar {:aot :all}}

  :cljsbuild {
    :builds {
             :dev
             {:source-paths ["src/cljs"]
              :compiler {
                :output-to "resources/public/js/main-debug.js"
                :output-dir "resources/public/js/out-debug"
                :optimizations :none
                :pretty-print true
                :source-map "resources/public/js/main-debug.js.map"}}
             :prod
             {:source-paths ["src/cljs"]
              :compiler {
                :output-to "resources/public/js/main.js"
                :output-dir "resources/public/js/out"
                :optimizations :advanced
                :pretty-print false
                :preamble ["react/react.min.js"]
                :externs ["react/externs/react.js"]
                :source-map "resources/public/js/main.js.map"}}
             }})
