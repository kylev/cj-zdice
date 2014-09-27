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
         :game-state nil}))

(println @app-state)

(defn handle-change [])

(defn do-roll [_]
  "Ask the server to perform a roll."
  (edn-xhr
   {:method :post
    :url (str "/game/" "TODO" "/roll")}))

(defn start-game [app]
  (edn-xhr
    {:method :post
     :url "/game"
     :on-complete #(om/transact! app :game-state (fn [_] %))}))

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
    om/IDisplayName
    (display-name [_] "StartButton")
    om/IRender
    (render [_]
      (dom/button
        #js {:onClick #(start-game app)
             :className "btn btn-primary"}
        "Start"))))

(defn action-buttons-view [app owner]
  (reify
    om/IDisplayName
    (display-name [_] "ActionButtons")
    om/IRender
    (render [_]
      (dom/div nil
        (dom/button
          #js {:onClick #(do-roll %)
               :className "btn"}
          "Roll")
        (dom/button
          #js {:onClick #(println @app)
               :className "btn"}
          "Stop")))))

(defn controls-view [app owner]
  (reify
    om/IDisplayName
    (display-name [_] "Controls")
    om/IInitState
    (init-state [_]
      {:playing false})
    om/IWillReceiveProps
    (will-receive-props [_ next-props] )
    om/IRenderState
    (render-state [_ {:keys [playing]}]
      (om/build (if (nil? (:game-state app)) action-buttons-view start-button-view) app))))

(defn game-view [app owner]
  (reify
    om/IDisplayName
    (display-name [_] "Game")
    om/IRender
    (render [_]
      (dom/div nil
        (om/build controls-view app)))))


(om/root game-view app-state
  {:target (gdom/getElement "game")})
