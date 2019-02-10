(defproject spiceview "0.1.0-SNAPSHOT"
  :description "A tool to view SPICE data and generate manifest files for loading into OpenSpace"
  :url "http://example.com/FIXME"
  :license {:name "Django Public License"
            :url "https://raw.githubusercontent.com/django/django/master/LICENSE"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]

                 ;; Web UI
                 [aleph "0.4.6"]
                 [hiccup "1.0.5"]
                 [compojure "1.6.1"]

                 [com.rpl/specter "1.1.2"]

                 ]

  :plugins [
            [lein-ring "0.12.4"]

            ]
  :profiles {:dev
             {:plugins [
                        [com.jakemccrary/lein-test-refresh "0.23.0"]
                             ;;[lein-autotest "1.1.0"]
                        ]
              :dependencies [
                             [midje "1.9.6"]
                 ;;[com.stuartsierra/lazytest "1.1.2"]
                        ]
              }}
  :ring {:handler spiceview.handler/handler }
  )
