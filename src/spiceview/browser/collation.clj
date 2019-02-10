(ns spiceview.browser.collation
  (:require
    [spiceview.spice.human :as human]
    [clojure.string :as str]
    [com.rpl.specter :refer [transform MAP-VALS ALL]]
    )
  )

(defn rest-or-item [v]
  (if (= 1 (count v))
    (rest v)
    (first v)))

(defn- file-extension [s]
  (-> s
      (str/split #"\.")
      last))

(defn group-by-header [ss]
  (reduce (fn [ret x]
            (let [k (first x)
                  v (rest x)]
              (println "k: " k)
              (println "v: " v)
              (assoc ret k (conj (get ret k []) v))))
          {}
          ss)
  )
  ;;(let [m (->> ss
  ;;             (group-by first))]
  ;;  m
  ;;(transform [MAP-VALS ALL] rest
  ;;           ;;rest-or-item
  ;;           m)
  ;;))

(defn parse-filenames [ss]
  ;; group by extension
  (->> ss
       ;;(map #(str/split % #"[._]"))
       (group-by file-extension)
       ;;(group-by last)
       ;;(map drop-last)

  ;; group by first common substring

  )
  )
