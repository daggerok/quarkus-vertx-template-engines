# quarkus / vertx template engines [![Build Status](https://travis-ci.org/daggerok/quarkus-vertx-template-engines.svg?branch=master)](https://travis-ci.org/daggerok/quarkus-vertx-template-engines)
Revisit MVC templating solutions we have in Quarkus and in Vertx as well...

## handlebars

Features:

* Manually render engine results
* Automatically delegate rendering to engine according to request paths regex. Use it if you want configure once how pages should be routed depends on theirs paths 
* Combine Vertx MVC Templates rendering with RESTEasy REST API on single server
* Vertx + RESTEasy with RESTEasy Fallback
* Quarkus native is supported!

```bash
./mvn -f handlebars clean compile quarkus:dev
http :8080
http :8080/about
http :8080/fallback.html
http :8080/not-found
```

## resources

* http://jknack.github.io/handlebars.java/
