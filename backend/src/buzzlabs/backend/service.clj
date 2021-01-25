(ns buzzlabs.backend.service
  (:require [br.com.example.service.client.Example.server :as example]
            #_[service.example.client.Example.server :as example]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [ring.util.response :as ring-resp]
            [protojure.pedestal.core :as protojure.pedestal]
            [protojure.pedestal.routes :as proutes]
            [buzzlabs.example.handler :as handler]
            )
  (:import  (buzzlabs.example.handler ExampleService)))

(def common-interceptors [(body-params/body-params) http/html-body])

(def routes #{})


(def grpc-routes (reduce conj routes (proutes/->tablesyntax {:rpc-metadata     example/rpc-metadata
                                                             :interceptors     common-interceptors
                                                             :callback-context (ExampleService.)})))
(def service {:env                  :prod
              ::http/routes         grpc-routes

              ::http/type           protojure.pedestal/config
              ::http/chain-provider protojure.pedestal/provider

              ::http/host          "0.0.0.0"
              ::http/port           8080})
