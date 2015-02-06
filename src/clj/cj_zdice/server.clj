(ns cj-zdice.server
  (:gen-class)
  (:require [ring.util.response :refer [resource-response]]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.edn :refer [wrap-edn-params]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [environ.core :refer [env]]
            [cj-zdice.game :as game]
            [cj-zdice.store :as gstore]
            [cj-zdice.page :as page]))

;; UUID as game identifier.
(defn uuid [] (str (java.util.UUID/randomUUID)))
(def uuid-regex #"[a-f\d\-]{36}")
;; (uuid)

(defn edn-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(defn html-response [template & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "text/html"}
   :body (apply str template)})

;; URL handlers
(defn index []
  (resource-response "public/html/index.html"))

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
  (GET "/blah" [] (html-response (page/blah "hello")))
  (route/resources "/" {:root "public"})
  )

(def app
  (-> routes))

;; This is the dev server
;;(defonce server
;;  (run-jetty #'app {:port 8081 :join? false}))

;; Heroku friendly server
(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) "5000"))]
    (run-jetty #'app {:port port :join? false})))
