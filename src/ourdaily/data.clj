(ns ourdaily.data
  (:require [environ.core :refer [env]]))

;; functions to turn db schema into maps with keys as id


(defn build-map [db]
  (let [items    (:items db)
        id       (:id db)
        to-pairs (fn [i] [(id i) i])]
    (apply hash-map (mapcat to-pairs items))))

;; app data

(def db-projects {:id :name
                  :items #{{:name "Primeiro Projeto"}}})

(def db-users {:id :email
               :items #{{:email "lucasmreis@gmail.com" :name "Lucas Reis"}
                        {:email "lucas@stereocause.com" :name "Lucas SC"}}})

(def projects (build-map db-projects))

(def users (build-map db-users))

