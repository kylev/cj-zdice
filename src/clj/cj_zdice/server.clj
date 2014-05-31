(ns cj-zdice.server
  (:require [ring.util.response :refer [file-response]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.edn :refer [wrap-edn-params]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [cj-zdice.game :as game]
            [cj-zdice.store :as gstore]))

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

(defn start-game-request []
  (let [new-game (assoc (game/new) :id (uuid))]
    (gstore/save-game new-game)
    (edn-response new-game)))

(defn get-game-request
  "Get a stored game. 404 if it doesn't exist."
  [id]
  (let [game (gstore/load-game id)]
    (if (nil? game)
      {:status 404}
      (edn-response game))))

(defn roll-dice-request
  "Perform a roll transformation on the games state."
  [id])

;; Routes and the app
(defroutes routes
  (GET "/" [] (index))
  (POST "/game" [] (start-game-request))
  (GET ["/game/:id" :id uuid-regex] [id] (get-game-request id))
  (POST ["/game/:id/roll" :id uuid-regex] [id] (roll-dice-request id))
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
