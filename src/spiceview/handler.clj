(ns spiceview.handler
  (:require
    [aleph.http :as http]
    [compojure.core :as compojure :refer [GET POST]]
    [compojure.route :as route]))

(def handler
  (params/wrap-params
    (compojure/routes
      (GET "/" [] "<h1>Hello World</h1>")
      (route/not-found "<h1>Page not found</h1>"))))

(defn start []
  (def server (http/start-server handler {:port 8080})))

(defn stop []
  (.close server)
  (start))
