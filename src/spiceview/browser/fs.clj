(ns spiceview.browser.fs
  (:require
   [spiceview.spice.human :as human]
   [spiceview.browser.collation :as collation]
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

(defn- file-extension [f]
  (-> (.getName f)
      (str/split #"\.")
      last))

(defn list-data-files [d]
  (->> (.listFiles d)
       (remove #(.isDirectory %))
       (remove #(= "txt" (file-extension %)))
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
    (list-data-files d)))

(defn ls-juno []
  ;;(my-ls (File. "resources/data/naif.jpl.nasa.gov/pub/naif/JUNO/kernels/"))
  (list-submodules (File. "resources/data/naif.jpl.nasa.gov/pub/naif/JUNO/kernels/"))
  )


;;(ls-juno)

(->>
 (submodule-contents "JUNO" "ck")
 (map last)
 (map #(.getName %))
 (map #(str/split % #"[._]"))
 (group-by (partial take 3))
 first
      ;;(collation/group-by-header)
 ;;keys
    )

;;(file-extension (File. "resources/data/naif.jpl.nasa.gov/pub/naif/JUNO/kernels/ck/aareadme.txt"))


;;(str/split "readme.txt" #"\.")
