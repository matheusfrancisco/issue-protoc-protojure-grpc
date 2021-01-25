package main

import (
	"context"
	"fmt"
	"io"
	"log"
	"time"

	e "github.com/matheusfrancisco/issue-protoc-protojure-grpc/proto"
	"google.golang.org/grpc"
	"google.golang.org/grpc/metadata"
	"google.golang.org/protobuf/types/known/timestamppb"
)

type Period struct {
	FromDate time.Time //oldest
	ToDate   time.Time //latest
}

func main() {
	log.Println("Client running ...")
	ctx, cancel := context.WithTimeout(context.Background(), 10000*time.Millisecond)
	defer cancel()
	conn, err := grpc.Dial(
		"localhost:8080",
		grpc.WithInsecure(),
		grpc.WithBlock(),
		grpc.WithTimeout(time.Duration(1000)*time.Second),
	)

	if err != nil {
		log.Fatalln(err)
	}

	defer conn.Close()

	c := e.NewExampleClient(conn)

	md, ok := metadata.FromOutgoingContext(ctx)

	if !ok {
		md = metadata.New(nil)
	} else {
		md = md.Copy()
	}

	ctx = metadata.NewOutgoingContext(ctx, md)

	request := &c.AuthRequest{
		Email: "matheusmachadoufsc@gmail.com",
		Password:      "123123",
	}

	r, err := c.Authenticate(ctx, request)

	if err != nil {
		log.Fatalf("failed auth: %v", err)
	}

	fmt.Println(r, "auth response")

}
