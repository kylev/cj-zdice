(ns cj-zdice.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.element :only [link-to]]))

(defpartial link-bar []
  [:ul.nav
    [:li (link-to "/about" "About")]])

(defpartial page-header []
  [:header
    [:div.ym-wrapper [:div.ym-wbox
    [:h1 "Zombie Dice"]]]])

(defpartial page-footer []
  [:footer
    [:div.ym-wrapper [:div.ym-wbox
    [:p "© Kyle V 2012. Zombie Dice is the property of " (link-to "http://www.sjgames.com/" "Steve Jackson Games") "."]]]])


(defpartial amazon []
  [:iframe {:src "http://rcm.amazon.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=kylevcom-20&o=1&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B003IKMR0U"
          :style "width:120px;height:240px;" :scrolling "no" :marginwidth "0" :marginheight "0" :frameborder "0"}])

(defpartial layout [& content]
  (html5
    [:head
      [:title "Zombie Dice"]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}] ; YAML mobile viewport optimization
      (include-css "/css/zd.css")
      (include-js "/js/jquery-1.7.2.min.js" "/js/backbone-min.js" "/js/zd.js")]
    [:body
      (page-header)
      [:div#main [:div.ym-wrapper [:div.ym-wbox
        [:div.ym-grid [:div.ym-g80.ym-gl content] [:div.ym-g20.ym-gr (amazon)]]]]]
      (page-footer)]))
