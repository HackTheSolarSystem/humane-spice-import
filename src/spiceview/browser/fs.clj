(ns spiceview.browser.fs
 (:require
   [spiceview.spice.human :as human]
   )
 (:import java.io.File)
 )

(defn my-ls [d]
  (println "Files in " (.getName d))
  (doseq [f (.listFiles d)]
    (if (.isDirectory f)
      (print "d ")
      (print "- "))
    (println (.getName f))))

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

(defn ls-juno []
  ;;(my-ls (File. "resources/data/naif.jpl.nasa.gov/pub/naif/JUNO/kernels/"))
  (list-submodules (File. "resources/data/naif.jpl.nasa.gov/pub/naif/JUNO/kernels/"))
  )

(ls-juno)
;;(ls-juno)
