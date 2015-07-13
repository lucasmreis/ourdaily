(ns ourdaily.reader
  (:require [clojure-mail.core :refer :all]
            [clojure-mail.message :as message]
            [clj-time.core :as t]
            [clj-time.coerce :as c]))

(defn build-map [m]
  (let [msg   (message/read-message m)
        email (.getAddress (:from msg))
        text  (.toString (:from msg))]
    (assoc msg :from {:email email :text text})))

(defn read-message [m]
  {:msg (build-map m)
   :date-sent (.getSentDate m)})

(defn last-hours-from [date-time-obj hours]
 (.minusHours date-time-obj hours))

(defn is-in-last-hours-from [date-time-obj hours]
 (fn [msg]
  (t/after?
   (c/from-date (:date-sent msg))
   (last-hours-from date-time-obj hours))))

(defn ourdaily-store [username password]
  (gen-store username password))

(defn get-messages-from [date-time-obj hours store]
 (map :msg (take-while
            (is-in-last-hours-from date-time-obj hours)
            (map read-message (inbox store)))))

(defn get-messages [username password hours]
  (get-messages-from
   (t/now)
   hours
   (ourdaily-store username password)))
