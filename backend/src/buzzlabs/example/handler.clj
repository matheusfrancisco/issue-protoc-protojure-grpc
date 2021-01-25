(ns buzzlabs.example.handler
  (:require [br.com.service.example.client.Example.server :as example]
            #_[service.example.client.Example.server :as example]
            [protojure.grpc.status :as status]
            [clojure.core.async :as async]
            [clojure.walk :as walk]
            [clj-time.core :as tc]))



(deftype ExampleService []
  example/Service

  (Authenticate
    [_ {{:keys [_ _] :as req} :grpc-params :as request}]
      {:status 200
       :body   {:session-id  "mock-id"}}))
