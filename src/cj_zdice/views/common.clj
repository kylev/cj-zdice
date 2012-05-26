(ns cj-zdice.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.element :only [link-to]]))

; YAML helpers

(defpartial ym-wrap [content] [:div.ym-wrapper [:div.ym-wbox content]])

; Layout compontents

(defpartial page-header []
  [:header
    (ym-wrap [:h1 "Zombie Dice"])])

(defpartial page-nav []
  [:nav#nav
    [:div.ym-wrapper.ym-hlist
      [:ul
        [:li (link-to "/about" "About")]
        [:li (link-to "/score" "Scorer")]]]])

(defpartial page-footer []
  [:footer
    (ym-wrap 
      [:p "© Kyle V 2012. Zombie Dice is the property of " (link-to "http://www.sjgames.com/" "Steve Jackson Games") "."])])

; Monetization

(defpartial amazon []
  [:iframe {:src "http://rcm.amazon.com/e/cm?lt1=_blank&bc1=000000&IS2=1&bg1=FFFFFF&fc1=000000&lc1=0000FF&t=kylevcom-20&o=1&p=8&l=as4&m=amazon&f=ifr&ref=ss_til&asins=B003IKMR0U"
            :style "width:120px;height:240px;" :scrolling "no" :marginwidth "0" :marginheight "0" :frameborder "0"}])

; Core layout

(defpartial layout [& content]
  (html5
    [:head
      [:title "Zombie Dice"]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}] ; YAML mobile viewport optimization
      (include-css "/css/zd.css")
      (include-js "/js/jquery-1.7.2.min.js" "/js/jquery-ui-1.8.20.custom.min.js" "/js/zd.js")]
    [:body
      (page-header)
      (page-nav)
      [:div#main (ym-wrap content)]
      (page-footer)]))
