(defproject central "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.6.1"]
                 [compojure "1.6.0"]
                 [ring/ring-defaults "0.3.0"]
                 [cerber/cerber-oauth2-provider "0.1.8"]
                 [commons-codec "1.6"]
                 [commons-codec "1.10"]]
  :main ^:skip-aot central.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
