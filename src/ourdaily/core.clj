(ns ourdaily.core
  (:require [clojure-mail.core :refer :all]
            [clojure-mail.message :as message]
            [environ.core :refer [env]]))

(def gmail-username (env :gmail-username))
(def gmail-password (env :gmail-password))

(def my-store (gen-store gmail-username gmail-password))
