(ns cj-zdice.views.welcome
  (:require [cj-zdice.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.page-helpers]))

(defpage "/" []
  (common/layout
    [:p "Welcome to cj-zdice"]))

(defpage "/about" []
  (common/layout
    [:h1 "About CJ-Zdice"]
    [:p "This is going to be " (link-to "somewhere" "Zombie Dice") " in Clojure."]))
