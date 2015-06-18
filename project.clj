(defproject ourdaily "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [io.forward/clojure-mail "1.0"]
                 [com.draines/postal "1.11.3"]
                 [environ "1.0.0"]
                 [clj-time "0.9.0"]]
  :repl-options {:init-ns ourdaily.core})
