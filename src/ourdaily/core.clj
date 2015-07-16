(ns ourdaily.core
  (:require [environ.core :refer [env]]
            [ourdaily.data :as db]
            [ourdaily.reader :refer [get-messages]]
            [ourdaily.parser.project :refer [attach-project]]
            [ourdaily.parser.user :refer [attach-user]]
            [ourdaily.parser.contents :refer [attach-contents]]
            [ourdaily.render :refer [render-digest]]))


;; config

(def server-user {:username (env :server-username)
                  :password (env :server-password)})

(def hours-window 240)

;; ---

(defn is-valid [m]
  (not (or (nil? (:project m)) (nil? (:user m)))))

(defn start []
  (let [user             (:username server-user)
        password         (:password server-user)
        messages         (get-messages user password hours-window)
        p-attach-project (partial attach-project db/projects)
        p-attach-user    (partial attach-user db/users)]

    (->> messages
         (map p-attach-project)
         (map p-attach-user)
         (filter is-valid)
         (map attach-contents)
         (map db/join-to-msg)
         (group-by #(:name (:project %)))
         (vals)
         (map render-digest))))
