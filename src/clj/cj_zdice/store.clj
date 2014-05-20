(ns cj-zdice.store
  (:require [taoensso.carmine :as car :refer (wcar)]))


(def prefix "cjzd")

;; Redis connection (TODO configs)
(def redis-conn {:pool {} :spec {}})
(defmacro wcar* [& body]
  `(car/wcar redis-conn ~@body))

(defn ns-key
  "Generate a namespaced key for shared redis."
  [key]
  (str prefix ":" key))

(defn save
  [game]
  (wcar* (car/set (ns-key (:id game)) game)))

(defn load
  [game-id]
  (wcar* (car/get (ns-key game-id))))
