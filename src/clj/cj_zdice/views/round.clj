(ns cj-zdice.views.round
  (:require [cj-zdice.views.common :as common]
            [cj-zdice.models.game :as game])
  (:use [noir.core :only [defpartial defpage]]
        [noir.response :only [json]]))

(defn uuid [] (str (java.util.UUID/randomUUID)))

(defpage "/round" [] (json game/dice-cup))

(defpage "/round/:id" {:keys [id]}
  (common/layout
    [:p "Game page for " id]))
