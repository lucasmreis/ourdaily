(ns ourdaily.parser.contents
  (:require [clojure.string :refer [split trim lower-case]]))

(defn between [from until s]
  (try
    (let [after-from   (second (split s from))
          before-until (first (split after-from until))]
      (trim before-until))
    (catch Exception e nil)))

(defn build-contents [s]
  {:did    (between #"#did" #"#willdo" s)
   :willdo (between #"#willdo" #"#imp" s)
   :imp    (between #"#imp" #"#end" s)})

(defn get-text [m]
  (let [body      (:body m)
        condition (fn [b] (re-find #"text/plain" (lower-case (:content-type b))))
        texts     (filter condition body)]
    (:body (first texts))))

(defn attach-contents [m]
  (let [text     (get-text m)
        contents (build-contents text)]
  (assoc m :contents contents)))


