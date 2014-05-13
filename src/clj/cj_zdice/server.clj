(ns cj-zdice.server
  (:require [ring.util.response :refer [file-response]]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]))

(defn index []
  (file-response "public/html/index.html" {:root "resources"}))

(defroutes routes
  (GET "/" [] (index))
  (route/files "/" {:root "resources/public"}))

(def app
  (-> routes))

;(defonce server
;  (run-jetty #'app {:port 8080 :join? false}))


;; Disabled server stuff for now.
(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "5000"))]
    (run-jetty #'app {:port 5000 :join? false})))

