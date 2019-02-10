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
        [:li [:a {:href (str "/mission/juno/" name)} description]])]

     ]
    ))

(defn submodule-handler [{{submodule :submodule} :params}]
  (str "this is a submodule: "
    submodule
    ))

(def handler
  (params/wrap-params
    (compojure/routes

      (GET "/" [] "<h1>Hello World</h1>")

      (GET "/mission/juno" [] juno-handler)
      (GET "/mission/juno/:submodule" [] submodule-handler)

      (route/not-found "<h1>Page not found</h1>")

      )))

(defn start []
  (def server (http/start-server handler {:port 8080})))

(defn stop []
  (.close server)
  (start))
