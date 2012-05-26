(ns cj-zdice.views.score
  (:require [cj-zdice.views.common :as common])
  (:use [noir.core :only [defpartial defpage]]))

(defpage "/score" []
  (common/layout
    [:ul#playerlist
      [:li "Player 1"]
      [:li "Player 2"]]))
