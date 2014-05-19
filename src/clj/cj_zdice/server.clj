(ns cj-zdice.server
  (:require [ring.util.response :refer [file-response]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.edn :refer [wrap-edn-params]]
            [taoensso.carmine :as car :refer (wcar)]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [cj-zdice.game :as game]))

;; Redis connection (TODO configs)
(def redis-conn {:pool {} :spec {}})
(defmacro wcar* [& body]
  `(car/wcar redis-conn ~@body))

;; UUID as game identifier.
(defn uuid [] (str (java.util.UUID/randomUUID)))
(def uuid-regex #"[a-f\d\-]{36}")

(defn edn-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

;; URL handlers
(defn index []
  (file-response "public/html/index.html" {:root "resources"}))

(defn start-game []
  (edn-response (assoc (game/new) :id (uuid))))

(defn get-game [id]
  "Thinger")

;; Routes and the app
(defroutes routes
  (GET "/" [] (index))
  (POST "/game" [] (start-game))
  (GET ["/game/:id" :id uuid-regex] [id] (get-game id))
  (route/files "/" {:root "resources/public"}))

(def app
  (-> routes))

(defonce server
  (run-jetty #'app {:port 8080 :join? false}))


;; Disabled server stuff for now.
(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "5000"))]
    (run-jetty #'app {:port port :join? false})))

