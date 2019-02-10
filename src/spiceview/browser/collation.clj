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

(defn map-function-on-map-vals
  "Take a map and apply a function on its values. From [1].
   [1] http://stackoverflow.com/a/1677069/500207"
  [m f]
  (zipmap (keys m) (map f (vals m))))

(defn nested-group-by
  "Like group-by but instead of a single function, this is given a list or vec
   of functions to apply recursively via group-by. An optional `final` argument
   (defaults to identity) may be given to run on the vector result of the final
   group-by."
  [fs coll & [final-fn]]
  (if (empty? fs)
    ((or final-fn identity) coll)
    (map-function-on-map-vals (group-by (first fs) coll)
                              #(nested-group-by (rest fs) % final-fn))))

(defn third [xs]
  (nth xs 2))

(defn group-by-header [ss]
  ;;(group-by first ss))
  (nested-group-by [first second] ss
                   ;;(partial mapv last)
                   (partial map last)
                   ))
  ;; (let [ret (reduce (fn [ret x]
  ;;                     (let [k (first x)
  ;;                           v (rest x)]
  ;;                       (assoc ret k (conj (get ret k []) v))))
  ;;                   {}
  ;;                   ss)]
  ;;   (into {}
  ;;         (for [[k v] ret]
  ;;           [k (if (= (count v) 1)
  ;;                (first v)
  ;;                (group-by-header v))]))))

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
