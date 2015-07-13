(ns ourdaily.core
  (:require [environ.core :refer [env]]
            [ourdaily.data :as db]
            [ourdaily.reader :refer [get-messages]]
            [ourdaily.parser.project :refer [attach-project]]
            [ourdaily.parser.user :refer [attach-user]]
            [ourdaily.parser.contents :refer [attach-contents]]))


;; config

(def server-user {:username (env :server-username)
                  :password (env :server-password)})

(def hours-window 240)

;; ---

(defn is-valid [m]
  (not (or (nil? (:project m)) (nil? (:user m)))))

(defn start []
  (->> (get-messages (:username server-user) (:password server-user) hours-window)
       (map (partial attach-project db/projects))
       (map (partial attach-user db/users))
       (filter is-valid)
       (map attach-contents)
       (group-by :project)))
