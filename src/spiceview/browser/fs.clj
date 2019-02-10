(ns spiceview.browser.fs
  (:require
   [spiceview.spice.human :as human]
   [clojure.string :as str]
   )
  (:import java.io.File)
  )

;; (def base-dir "/home/dk/workspace/current/spiceview/resources/data/naif.jpl.nasa.gov/pub/naif")
(def base-dir "resources/data/naif.jpl.nasa.gov/pub/naif")

(defn my-ls [d]
  (println "my-ls")
  (println "Files in " (.getName d))
  (doseq [f (.listFiles d)]
    (if (.isDirectory f)
      (print "d ")
      (print "- "))
    (println (.getName f))))

(defn list-files [d]
  (->> (.listFiles d)
       (remove #(.isDirectory %))
       (map #(vector (.getName %) %))
       (into {})))

(defn list-submodules [d]
  (->> (.listFiles d)
       (filter #(.isDirectory %))
       (map #(vector
               (.getName %)
               {:description (human/dirtypes (.getName %))
                :name (.getName %)
                :file %}))
       ;;(sort-by #(.getName (last %)))
       (into {})))

(defn submodule-contents [mission submodule]
  (let [d (File. (str/join "/"
            [base-dir
             mission
             "kernels"
             submodule]))]
    (list-files d)))

(defn ls-juno []
  ;;(my-ls (File. "resources/data/naif.jpl.nasa.gov/pub/naif/JUNO/kernels/"))
  (list-submodules (File. "resources/data/naif.jpl.nasa.gov/pub/naif/JUNO/kernels/"))
  )

;;(ls-juno)

(-> (submodule-contents "JUNO" "ck")
    ;;(.exists)
    )
