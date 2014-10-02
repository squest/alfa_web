(ns alfa.home.ctrl
  (:require [alfa.home.pages :as page]))

(defn homepage
  []
  (page/homepage))

(defn nothomepage
  [message]
  (page/nothomepage message))

(defn generic
  [mapkey]
  (page/generic mapkey))



