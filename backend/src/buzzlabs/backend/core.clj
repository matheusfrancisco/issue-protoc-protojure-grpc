(ns buzzlabs.backend.core
  (:gen-class)
  (:require [io.pedestal.http :as server]
            [io.pedestal.http.route :as route]
            [buzzlabs.backend.service :as service]
            [taoensso.timbre :as log]
            [taoensso.timbre.appenders.core :as appenders]
            [mount.core :as mount]))


(mount/defstate translations-server
                :start (-> service/service
                           server/create-server
                           server/start)
                :stop (server/stop translations-server))

(defn go []
  (mount/start)
  :ready)

(defn stop []
  (mount/stop))

#_(defn reset []
  (mount/stop)
  (tn/refresh :after 'go))

(defn -main []
  (go))

(comment


  (go)

  (stop)

  ;; -- REPL
  )