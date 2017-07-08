(ns central.core
  (:gen-class)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer :all]
            [cerber.handlers :as handlers]
            [mount.core :as m]
            [cerber.oauth2.context :as ctx]
            [ring.middleware.defaults :refer [api-defaults wrap-defaults]]))

(m/start-with-args {:env "local" :base-name "central"})

(defroutes oauth-routes
  (GET  "/authorize" [] handlers/authorization-handler)
  (POST "/approve"   [] handlers/client-approve-handler)
  (GET  "/refuse"    [] handlers/client-refuse-handler)
  (POST "/token"     [] handlers/token-handler)
  (GET  "/login"     [] handlers/login-form-handler)
  (POST "/login"     [] handlers/login-submit-handler))

(defn user-info-handler [req]
  {:status 200
   :body (::ctx/user req)})

(defroutes restricted-routes
  (GET "/user/info" [] user-info-handler))



(def app
 (wrap-defaults
  (routes
   oauth-routes
   (wrap-routes restricted-routes handlers/wrap-authorized))
  api-defaults))

; (defroutes app
;   (GET "/" [] "<h1>Hello World</h1>")
;   (route/not-found "<h1>Page not found</h1>"))

(defn -main
  [& args]
  (run-jetty app {:port 4000}))
