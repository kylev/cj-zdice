(ns cj-zdice.page
  (:require [net.cgrand.enlive-html :as html]))

;;(html/html-resource "templates/app-script.html")

;;(html/defsnippet app-script-debug "templates/app-script-debug.html" [] [])

(html/deftemplate blah "templates/blah.html"
  [title]
  [:head :title] (html/content title)
  [:h1] (html/content title))
