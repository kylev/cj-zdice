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
;; (uuid)

(defn edn-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

;; URL handlers
(defn index []
  (file-response "public/html/index.html" {:root "resources"}))

(defn start-game []
  (edn-response (assoc (game/new) :id (uuid))))

;; (def test-game "89230829-9d8f-4dfe-9c46-c93d5620e8f1")
;; (wcar* (car/set test-game {}))
;; (wcar* (car/get test-game))

(defn get-game-request
  "Get a stored game. 404 if it doesn't exist."
  [id]
  (let [game (wcar* (car/get id))]
    (if (nil? game)
      {:status 404}
      (edn-response game))))

(defn roll-dice [id])

;; Routes and the app
(defroutes routes
  (GET "/" [] (index))
  (POST "/game" [] (start-game))
  (GET ["/game/:id" :id uuid-regex] [id] (get-game-request id))
  (POST ["/game/:id/roll" :id uuid-regex] [id] (roll-dice id))
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

(defn -heroku
  "Heroku-specialized server runner."[& m]
  nil)
