(ns spiceview.handler
  (:require
    [aleph.http :as http]
    [compojure.core :as compojure :refer [GET POST]]
    [compojure.route :as route]
    [ring.middleware.params :as params]
    [hiccup.core :as hiccup :refer [html]]

    [spiceview.browser.fs :as fs]
    ))

(defn juno-handler [req]
  (html
    [:div
     [:h1 "Juno Mission"]

     [:ul
      (for [[k {:keys [description name file]}] (fs/ls-juno)]

        [:li [:a {:href (str "/mission/JUNO/" name)} description]])]

     ]
    ))

(defn submodule-handler [{{:keys [mission submodule] } :params}]
  (html
    [:div

     (format "this is a submodule: %s from mission: %s" submodule mission)

     [:h2 "List:"]
     [:ul
      (for [[n f] (fs/submodule-contents mission submodule)]
        [:li
         [:a {:href "//google.com"} n]])]
     ]
     ))

(def handler
  (params/wrap-params
    (compojure/routes

      (GET "/" [] "<h1>Hello World</h1>")

      (GET "/mission/JUNO" [] juno-handler)
      (GET "/mission/:mission/:submodule" [] submodule-handler)

      (route/not-found "<h1>Page not found</h1>")

      )))

(defn start []
  (def server (http/start-server handler {:port 8080})))

(defn stop []
  (.close server)
  (start))
