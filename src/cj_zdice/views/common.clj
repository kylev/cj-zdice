(ns cj-zdice.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.element :only [link-to]]))

(defpartial link-bar []
  [:ul.nav
    [:li (link-to "/about" "About")]])

(defpartial amazon []
  [:iframe {:src "http://rcm.amazon.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=kylevcom-20&o=1&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B003IKMR0U"
          :style "width:120px;height:240px;" :scrolling "no" :marginwidth "0" :marginheight "0" :frameborder "0"}])

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
