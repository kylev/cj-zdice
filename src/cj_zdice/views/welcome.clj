(ns cj-zdice.views.welcome
  (:require [cj-zdice.views.common :as common])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]
        [hiccup.element :only [link-to]]))

(defpage "/" []
  (common/layout
    [:p "Welcome to cj-zdice.  This is a bunch of text so that things push out a little bit and that I can maybe see how things lay out."]))

(defpage "/about" []
  (common/layout
    [:p "I am attempting to learn "
        (link-to "http://www.clojure.org/" "Clojure")
        " and "
        (link-to "http://www.webnoir.org/" "Noir")
        " by implementing the excellent game of "
        (link-to "http://www.sjgames.com/dice/zombiedice/" "Zombie Dice")
        ". You should really just go "
        (link-to "http://www.amazon.com/gp/product/B003IKMR0U/ref=as_li_ss_tl?ie=UTF8&tag=kylevcom-20&linkCode=as2&camp=1789&creative=390957&creativeASIN=B003IKMR0U" "buy it")
        "."]
    ))
