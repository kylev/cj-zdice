(ns cj-zdice.core
  (:require [goog.dom :as gdom]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)
;;(println "Hello world!")

(def app-state
  (atom {:game {}}))

(defn game-view [app owner]
  (reify
    om/IRender
    (render [_] (dom/div nil "Stuff!"))))

(om/root game-view app-state
  {:target (gdom/getElement "game")})
