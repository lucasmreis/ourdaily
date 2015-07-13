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
        :else (recur (rest rest-names)
                     (first rest-names)
                     (re-find (to-regex (first rest-names)) subject))))))

(defn get-user [users m]
  (let [email  (:email (:from m))
        emails (keys users)]

    (loop [rest-emails emails
           current    nil]
      (cond
       (= current email)   current
       (empty? rest-emails) nil
       :else (recur (rest rest-emails) (first rest-emails))))))


(defn attach-project [projects m]
  (if-let [proj (get-project projects m)]
    (assoc m :project proj)
    (assoc m :project nil)))

(defn attach-user [users m]
  (if-let [user (get-user users m)]
    (assoc m :user user)
    (assoc m :user nil)))
