(ns ourdaily.reader
  (:require [clojure-mail.core :refer :all]
            [clojure-mail.message :as message]
            [environ.core :refer [env]]
            [clj-time.core :as t]
            [clj-time.coerce :as c]))

(def gmail-username (env :gmail-username))
(def gmail-password (env :gmail-password))

(defn- read-message [m] {:msg (message/read-message m) :date-sent (.getSentDate m)})

(defn- last-day-from [date-time-obj]
 (.minusHours date-time-obj 24))

(defn- is-in-last-day-from [date-time-obj]
 (fn [msg]
  (t/after? (c/from-date (:date-sent msg)) (last-day-from date-time-obj))))

;; --------------------------------
;; public
;; --------------------------------

(defn ourdaily-store [] (gen-store gmail-username gmail-password))

(defn get-messages-from [date-time-obj store]
 (map :msg (take-while (is-in-last-day-from date-time-obj) (map read-message (inbox store)))))

(defn get-messages [] (get-messages-from (t/now) (ourdaily-store)))
