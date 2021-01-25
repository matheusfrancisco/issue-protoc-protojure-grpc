;;;----------------------------------------------------------------------------------
;;; Generated by protoc-gen-clojure.  DO NOT EDIT
;;;
;;; GRPC service.example.client.Example Client Implementation
;;;----------------------------------------------------------------------------------
(ns service.example.client.Example.client
  (:require [service.example.client :refer :all]
            [clojure.core.async :as async]
            [protojure.grpc.client.utils :refer [send-unary-params invoke-unary]]
            [promesa.core :as p]
            [protojure.grpc.client.api :as grpc]))

;-----------------------------------------------------------------------------
; GRPC Client Implementation
;-----------------------------------------------------------------------------

(defn Authenticate
  ([client params] (Authenticate client {} params))
  ([client metadata params]
  (let [input (async/chan 1)
        output (async/chan 1)
        desc {:service "service.example.client.Example"
              :method  "Authenticate"
              :input   {:f service.example.client/new-AuthRequest :ch input}
              :output  {:f service.example.client/pb->AuthResponse :ch output}
              :metadata metadata}]
    (-> (send-unary-params input params)
        (p/then (fn [_] (invoke-unary client desc output)))))))
