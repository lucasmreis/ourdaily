(ns ourdaily.parser.user)

(defn get-user [users m]
  (let [email  (:email (:from m))
        emails (keys users)]

    (loop [rest-emails emails
           current    nil]
      (cond
       (= current email)   current
       (empty? rest-emails) nil
       :else (recur (rest rest-emails) (first rest-emails))))))

(defn attach-user [users m]
  (if-let [user (get-user users m)]
    (assoc m :user user)
    (assoc m :user nil)))
