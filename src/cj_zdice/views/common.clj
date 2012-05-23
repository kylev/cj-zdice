(ns cj-zdice.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5 link-to]]))

(defpartial link-bar []
  [:ul.nav
    [:li (link-to "/about" "About")]])

(defpartial layout [& content]
  (html5
    [:head
      [:title "Zombie Dice"]
      (include-css "/css/reset.css")]
    [:body
      [:div#wrapper
        (link-bar)
        content]]))
