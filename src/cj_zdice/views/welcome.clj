(ns cj-zdice.views.welcome
  (:require [cj-zdice.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.element :only [link-to]]))

(defpage "/" []
  (common/layout
    [:p "Welcome to cj-zdice"]))

(defpage "/about" []
  (common/layout
    [:h1 "About CJ-Zdice"]
    [:p "I am attempting to learn "
        (link-to "htttp://www.clojure.org/" "Clojure")
        " by implementing the excellent game of "
        (link-to "http://www.sjgames.com/dice/zombiedice/" "Zombie Dice")
        ". You should really just go buy it."]
    (common/amazon)))
