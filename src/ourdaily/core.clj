(ns ourdaily.core
  (:require [environ.core :refer [env]]
            [ourdaily.reader :refer [get-messages]]))


;; CONFIG DATA

(def users #{"lucasmreis@gmail.com" "lucas@stereocause.com"})

(def server-user {:username (env :server-username)
                  :password (env :server-password)})

(def tokens {:subject "#ourdaily"
             :did "#did"
             :impediments "#impediments"
             :end "#end"})

;; -----

(defn is-user [m] (contains? users (.getAddress (:from m))))

(defn is-ourdaily [m] (re-find (re-pattern (:subject tokens)) (:subject m)))

(defn start []
  (->> (get-messages (:username server-user) (:password server-user) 10)
       (filter is-user)
       (filter is-ourdaily)))


