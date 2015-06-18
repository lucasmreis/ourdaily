(ns ourdaily.mail
  (:require [clojure-mail.core :refer :all]
            [clojure-mail.message :as message]
            [environ.core :refer [env]]
            [clj-time.core :as t]
            [clj-time.coerce :as c]))

(def gmail-username (env :gmail-username))
(def gmail-password (env :gmail-password))

(defn ourdaily-store [] (gen-store gmail-username gmail-password))

(defn- yesterday-from [date-time-obj]
  (.minusHours date-time-obj 24))

(defn- is-after [date-time-obj]
  (fn [msg]
    (t/after? (c/from-date (.getSentDate msg)) (yesterday-from date-time-obj))))

(defmacro safe-get
  "try to perform an action else just return nil"
  [& body]
  `(try
    (do ~@body)
  (catch Exception e#
    nil)))

(defn- read-message [msg]
  (try
    {:from (safe-get (.getSender msg))
     :subject (safe-get (.getSubject msg))
     :sent-date (safe-get (.getSentDate msg))
     :content (safe-get (message/message-body msg)) }
  (catch Exception e {:error e})))

(defn get-messages-after
  [date-time-obj store]
  ((comp
    (partial map read-message)
    (partial take-while (is-after date-time-obj)))
      (inbox (ourdaily-store))))
