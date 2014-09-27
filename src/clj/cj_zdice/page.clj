(ns cj-zdice.page
  (:require [net.cgrand.enlive-html :as html]))

(html/defsnippet app-script-debug "templates/app-script-debug.html" [:body] [] html/unwrap)

(html/defsnippet app-script "templates/app-script.html" [:body] [] html/unwrap)

(html/deftemplate blah "templates/blah.html"
  [title]
  [:head :title] (html/content title)
  [:h1] (html/content title)
  [:#app_js] (html/substitute (app-script)))

(html/sniptest app-script-debug)
