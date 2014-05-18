(ns cj-zdice.core
  (:require [goog.dom :as gdom]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)
(println "Hello world!")

(def app-state
  (atom {:user "foo"
         :scoreboard {}}))

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

(om/root game-view app-state
  {:target (gdom/getElement "game")})
