(defproject backend "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Apache License 2.0"
            :url  "https://www.apache.org/licenses/LICENSE-2.0"
            :year 2019
            :key  "apache-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [io.pedestal/pedestal.service "0.5.8"]

                 ;; -- PROTOC-GEN-CLOJURE --
                 [mount "0.1.16"]

                 [protojure "1.5.11"]
                 [protojure/google.protobuf "0.9.1"]

                 ;; -- PROTOC_GEN_CLOJURE CLIENT DEPS --
                 [org.eclipse.jetty.http2/http2-client "9.4.35.v20201120"]
                 [org.eclipse.jetty/jetty-alpn-java-client "9.4.35.v20201120"]
                 ;; -- Jetty Client Dep --
                 [org.ow2.asm/asm "9.0"]

                 [com.google.protobuf/protobuf-java "2.6.1"]

                 ;; Include Undertow for supporting HTTP/2 for GRPCs
                 [io.undertow/undertow-core "2.2.3.Final"]
                 [io.undertow/undertow-servlet "2.2.3.Final"]
                 ;; And of course, protobufs
                 [com.google.protobuf/protobuf-java "3.14.0"]
                 ;; logging
                 [com.taoensso/timbre "5.1.0"]
                 [com.fzakaria/slf4j-timbre "0.3.20"]

                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                 [org.slf4j/jul-to-slf4j "1.7.30"]
                 [org.slf4j/jcl-over-slf4j "1.7.30"]
                 [org.slf4j/log4j-over-slf4j "1.7.30"]
                 [amazonica "0.3.153"]
                 [clj-time "0.15.2"]

                 ]
  :min-lein-version "2.0.0"
  :resource-paths ["config", "resources", "service"]
  :profiles {:dev  {:aliases      {"run-dev" ["trampoline" "run" "-m" "buzzlabs.backend.server/run-dev"]}
                    :dependencies [[io.pedestal/pedestal.service-tools "0.5.8"]]}
             :prod {:aot          :all
                    :main         buzzlabs.backend.core
                    :uberjar-name "example.jar"}}
  :aliases {"release-prod" ["do"
                            ["clean"]
                            ["with-profile" "prod" "uberjar"]]}
  :main buzzlabs.backend.core
  )
