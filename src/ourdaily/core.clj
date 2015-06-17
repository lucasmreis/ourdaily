(ns ourdaily.core
  (:require [clojure-mail.core :refer :all]
            [clojure-mail.message :as message]
            [environ.core :refer [env]]
            [clj-time.format :as f]))

(def gmail-username (env :gmail-username))
(def gmail-password (env :gmail-password))

(def ourdaily-store (gen-store gmail-username gmail-password))

(def gmail-date-formatter (f/formatter "E MMM d H:m:s z y"))

(defn by-time
  [from to]
  (fn [msg]
    (and (> (:date-sent msg) from)
         (< (:date-sent msg) to))))

(defn get-messages
  [from to max-messages]
  filter)
