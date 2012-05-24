(ns cj-zdice.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.element :only [link-to]]))

(defpartial link-bar []
  [:ul.nav
    [:li (link-to "/about" "About")]])

(defpartial layout [& content]
  (html5
    [:head
      [:title "Zombie Dice"]
      (include-css "/css/reset.css")
      (include-js "/js/jquery-1.7.2.min.js" "/js/backbone-min.js")]
    [:body
      [:div#wrapper
        (link-bar)
        content]]))
