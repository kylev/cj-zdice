(ns cj-zdice.core
  (:require [goog.dom :as gdom]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)
(println "Hello world!")

(def app-state
  (atom {:user "foo"
         :game-id nil
         :scoreboard {}}))

(def do-roll)

(defn user-view [app owner]
  (reify
    om/IRender
    (render [_] (dom/div #js {:className "user"} (:user app)))))

(defn cup-view [app owner]
  (reify
    om/IRender
    (render [_] (dom/ul nil ""))))

(defn game-view [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil (om/build user-view app)))))


(defn action-buttons-view [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
        (dom/button
           #js {:onClick do-roll}
           "Roll")
        (dom/button nil "Stop & Score")))))

(defn rolled-die-view [app owner]
  )

(defn rolled-dice-view [app owner]
  "The set of rolled dice."
  (reify
    om/IRender
    (render [_]
      (dom/div #js {:className "dice"}
        (apply dom/ul nil
          (map (fn [die-state] "") []))))))

(defn game-view [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil "stuff")
      (om/build action-buttons-view app))))


(om/root game-view app-state
  {:target (gdom/getElement "game")})
