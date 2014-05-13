(ns cj-zdice.views.score
  (:require [cj-zdice.views.common :as common])
  (:use [noir.core :only [defpartial defpage]]
        [hiccup.element :only [javascript-tag]]))

(defpartial player-container [pname]
  [:li [:div.playerline (str pname ": ") [:span.score "0"]]
       [:div.controls [:button.nextplayer "Next"] [:button.score-plus "+"] [:button.score-minus "-"]]])

(defpage "/score" []
  (common/layout
    [:button#start "Start!"]
    [:ul#playerlist
      (player-container "First Player")
      (player-container "Second Player")]
    (javascript-tag "$(scorer_ready)")))