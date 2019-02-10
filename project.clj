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

                 ]

  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler spiceview.handler/handler }
  )
