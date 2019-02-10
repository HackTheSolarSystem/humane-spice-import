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
      (for [k (keys (fs/ls-juno))]
        [:li k])]

     ]
    ))

(def handler
  (params/wrap-params
    (compojure/routes

      (GET "/" [] "<h1>Hello World</h1>")

      (GET "/juno" [] juno-handler)

      (route/not-found "<h1>Page not found</h1>")

      )))

(defn start []
  (def server (http/start-server handler {:port 8080})))

(defn stop []
  (.close server)
  (start))
