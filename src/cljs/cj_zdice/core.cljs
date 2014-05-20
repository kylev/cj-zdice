(ns cj-zdice.core
  (:require [goog.dom :as gdom]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [om-sync.core :refer [om-sync]]
            [om-sync.util :refer [edn-xhr]]))

(enable-console-print!)
(println "Hello world!")

(def app-state
  (atom {:user "foo"
         :game-id nil
         :scoreboard {}}))

(defn do-roll [e] (println "Rolling"))

(defn start-game []
  (edn-xhr
    {:method :post
     :url "/game"
     :on-complete (fn [res] (println res))}))

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

(defn start-button-view [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/button
        #js {:onClick start-game}
        "Start"))))

(defn action-buttons-view [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
        (dom/button
           #js {:onClick do-roll}
           "Roll")
        (dom/button nil "Stop")))))

(defn game-view [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil
        (om/build start-button-view app)
        (om/build action-buttons-view app)))))


(om/root game-view app-state
  {:target (gdom/getElement "game")})
