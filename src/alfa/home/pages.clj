(ns alfa.home.pages
  (:require [net.cgrand.enlive-html :as html]))

(def dir "selmer/")

(def people
  [{:name "Joni" :status "Pujangga lapoex"}
   {:name "Jojon" :status "Purnawirawan jomblo!"}])

(defn sp-nothome
  [message]
  (html/html [:div {:class "large-12 columns"}
              [:h1 "This is part of the body"]
              [:hr]
              [:p (str "This is the message : " message)]]))

(defn list-of-people
  [data]
  (html/html [:div {:class "large-12 columns"}
              [:h1 "This is a list of people"]
              [:hr]
              (->> data
                   (map #(vector :li
                                 (str "Nama : " (:name %))
                                 [:br]
                                 (str "Status : " (:status %))))
                   (concat [:ul])
                   (into []))]))

(html/deftemplate home' (str dir "base.html")
  []
  [:#headline] (html/content "This is the headline")
  [:#subtitle] (html/content "This is the subtitle")
  [:#main] (html/content "This is the main body"))

(html/deftemplate nothome' (str dir "base.html")
  [message]
  [:#headline] (html/content message)
  [:#subtitle] (html/content "This is the subtitle")
  [:#main] (html/content (sp-nothome message)))

(html/deftemplate generic' (str dir "base.html")
  [data]
  [:#headline] (html/content "List of people")
  [:#subtitle] (html/content "This is the subtitle")
  [:#main] (html/content (list-of-people data)))

(defn homepage
  [] 
  (apply str (home')))

(defn nothomepage
  [message]
  (apply str (nothome' message)))

(defn generic
  [mapkey]
  (cond (= "people" mapkey)
        (apply str (generic' people))
        :else "Stuffs!")) 

(html/deftemplate play' (str dir "base.html")
  [message]
  [:#headline] (html/content message))

(defn play
  [message]
  (apply str (play' message)))
   
(defn sayhi
  [username]
  (apply str (play' (str "Hi " username))))

