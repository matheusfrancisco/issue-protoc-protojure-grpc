module github.com/matheusfrancisco/issue-protoc-protojure-grpc

go 1.14


require (
	github.com/matheusfrancisco/issue-protoc-protojure-grpc/proto v0.0.0-00010101000000-000000000000
	google.golang.org/grpc v1.35.0
	google.golang.org/protobuf v1.25.0
)

replace github.com/matheusfrancisco/issue-protoc-protojure-grpc/proto => ./service/service/
