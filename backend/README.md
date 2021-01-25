# Issue problem protoc-plugin clojure

This is the backend to show our problem with protoc-plugin for clojure.


## Reproduce the problem

You must be installed the [Protocol Buffer 'protoc' compiler](https://github.com/protocolbuffers/protobuf/releases)
and [Protojure protoc-plugin](https://github.com/protojure/protoc-plugin/releases).

#### Steps

Now, you run this command at your terminal `protoc --clojure_out=grpc-client,grpc-server:src --proto_path=. service/*.proto`
and you'll see the folder generated with the `java_package` path which is `option java_package = "br.com.service.example.client";
`.

Now you have to go to your golang client inside the folder `../go-client-grpc` and you must follow the steps there.

Now you need to run the backend server
```clojure
lein repl

(go)
```
and using golang client to call clojure backend go to `../go-client` and
run `go run main.go` and you'll see that doesn't work, because the client
doesn't found the Authenticate method declared in proto files.

to make this work you must remove `java_package` from proto file in `service/example.proto`
and run protoc again..  `protoc --clojure_out=grpc-client,grpc-server:src --proto_path=. service/*.proto` and you'll see the 
code create by protoc now will be in the namespace `service.example.client`, now you nee to go to `src/buzzlabs/backend/service.clj` and 
comment the import `[br.com.service.example.client.Example.server :as example]`  and uncommented 
`[service.example.client.Example.server :as example]` go to `src/buzzlabs/example/handler.clj` and do the same
comment and uncomment the imports, run the repl `lein repl` and run the server `(go)`

Now you go to `../go-client` and run go run main.go
it'll be work fine.


### Problem.

I think the problem was when  use use the java_package  the pkg contains br.com.service and client golang called
`service.example.client/Authenticate`
```clojure
(def ^:const rpc-metadata
  [{:pkg "br.com.service.example.client" 
    :service "Example"
    :method "Authenticate"
    :method-fn Authenticate-dispatch 
    :server-streaming false 
    :client-streaming false 
    :input pb->AuthRequest
    :output new-AuthResponse}])

```
and when we comment and generated without java_package the rpc-metadata is. That's means is the same pkg that was
invoked by golang client.
`service.example.client/Authenticate`

```clojure
(def ^:const rpc-metadata
  [{:pkg "service.example.client"
    :service "Example" 
    :method "Authenticate" 
    :method-fn Authenticate-dispatch 
    :server-streaming false
    :client-streaming false
    :input pb->AuthRequest 
    :output new-AuthResponse}])

```
