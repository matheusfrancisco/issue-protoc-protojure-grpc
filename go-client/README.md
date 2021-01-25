# Go client to backend in clojure using protojure

You must generated proto files, you need to install protoc

```
protoc --proto_path=. service/*.proto --go_out=plugins=grpc:service/
```

You'll see that proto in golang use the packag name to create grpc client.

Check in pb.go generated and see golang use package to invoke the method
`err := c.cc.Invoke(ctx, "/service.example.client.Example/Authenticate", in, out, opts...)`
wich means in the backend your serve needs have this method in these path..

