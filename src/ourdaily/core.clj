(ns ourdaily.core
  (:require [environ.core :refer [env]]
            [ourdaily.reader :refer [get-messages]]))


;; config

(def server-user {:username (env :server-username)
                  :password (env :server-password)})

(def hours-window 240)

;; ---

(defn start []
  (get-messages (:username server-user) (:password server-user) hours-window))
