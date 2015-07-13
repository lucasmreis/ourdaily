(ns ourdaily.core
  (:require [environ.core :refer [env]]
            [ourdaily.data :as db]
            [ourdaily.reader :refer [get-messages]]
            [ourdaily.parser :refer [attach-project attach-user]]))


;; config

(def server-user {:username (env :server-username)
                  :password (env :server-password)})

(def hours-window 240)

;; ---

(defn start []
  (->> (get-messages (:username server-user) (:password server-user) hours-window)
       (map (partial attach-project db/projects))
       (map (partial attach-user db/users))))
