(ns ourdaily.parser)

(def to-regex #(re-pattern (str "#" %)))

(defn get-project [projects m]
  (let [subject (:subject m)
        names   (keys projects)]

    (loop [rest-names names
           current    nil
           match      nil]
      (cond
        (not (nil? match))  current
        (empty? rest-names) nil
        :else (recur (rest names)
                     (first names)
                     (re-find (to-regex (first names)) subject))))))



(defn attach-project [projects m]
  (if-let [proj (get-project projects m)]
    (assoc m :project proj)
    (assoc m :project nil)))
