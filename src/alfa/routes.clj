(ns alfa.routes
  (:require [compojure.core :refer :all]
            [alfa.home.ctrl :as home]
            [alfa.home.pages :as page]
            [noir.session :as session]))

(defroutes home
  (GET "/" req
       (home/homepage))
  (GET "/otherpage/:message" [message]
       (home/nothomepage message))
  (GET "/test/:mapkey" [mapkey]
       (home/generic mapkey))
  (GET "/test1" req
       "This is just a route")
  (GET "/login/:username" [username]
       (do (session/put! :username username)
           (page/sayhi username)))
  (GET "/postlogin" req
       (let [user (session/get :username)]
         (page/sayhi user))))   

(defroutes play
  (context "/play" req
           (GET "/" req
                "This is a playful playground!!")
           (GET "/Iknow/:foo" req
                (let [{:keys [params]} req]
                  (page/play (:foo params))))))


