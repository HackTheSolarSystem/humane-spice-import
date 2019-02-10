(ns spiceview.handler
  (:require
    [aleph.http :as http]
    [compojure.core :as compojure :refer [GET POST]]
    [clojure.string :as str]
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

     ;; (format "this is a submodule: %s from mission: %s" submodule mission)
     (if (= submodule "ck")
       (let [grouped-data (->> (fs/submodule-contents "JUNO" "ck")
                               (map last)
                               (map #(.getName %))
                               (map #(str/split % #"[._]"))
                               (group-by (partial take 3)))]
         [:div
          [:form
           [:input {:name "begin" :type "date"}
            [:label "Begin Date"]]
           [:br]
           [:input {:name "begin" :type "date"}
            [:label "End Date"]]

           [:br]
           [:input {:value "hide-older" :name "vehicle1" :type "checkbox"}
            "Hide redundant older versions"]]


          [:form
           {:action "/action_page.php"}

           [:select
            {:multiple "multiple" :name "group" }
            (for [[n f] grouped-data]
              (let [s (str/join n "_")]
              [:option {:value n} n]))]

           [:select
            {:multiple "multiple" :name "files" }
            (for [n (first (vals grouped-data))
                  ;;[n f] (fs/submodule-contents mission submodule)
                  ]
              [:option {:value n} ])]

           ]
          ])
       [:div
        [:h2 "List:"]
        [:ul
         (for [[n f] (fs/submodule-contents mission submodule)]
           [:li
            [:a {:href "//google.com"} n]])]
        ]
       )
     ]))


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
