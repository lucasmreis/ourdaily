(ns ourdaily.send
  (:require [postal :refer [send-message]]
            [environ.core :refer [env]]))

(def gmail-username (env :gmail-username))
(def gmail-password (env :gmail-password))

(def conn {:host "smtp.gmail.com"
           :ssl true
           :user email
           :pass pass})

;; (send-message conn {:from email
;;                     :to email
;;                     :subject "A message, from the past"
;;                     :body "Hi there, me!"})
