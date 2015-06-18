(ns ourdaily.core
  (:require [clojure-mail.core :refer :all]
            [clojure-mail.message :as message]
            [environ.core :refer [env]]
            [clj-time.core :as t]
            [clj-time.coerce :as c]))


