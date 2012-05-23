(ns cj-zdice.views.welcome
  (:require [cj-zdice.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to cj-zdice"]))
