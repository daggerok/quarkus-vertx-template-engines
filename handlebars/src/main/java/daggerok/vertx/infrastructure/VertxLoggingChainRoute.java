package daggerok.vertx.infrastructure;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import static java.util.stream.Collectors.joining;

@ApplicationScoped
public class VertxLoggingChainRoute {

    @Inject
    Logger log;

    public void init(@Observes Router router) {
        Handler<RoutingContext> interceptor = rc -> {
            HttpServerRequest request = rc.request();
            String params = request.params()
                                   .entries()
                                   .stream()
                                   .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                                   .collect(joining(", "));
            log.infof("Vertx logging: %s %s %s", request.method(), request.path(), params);
            rc.next();
        };
        router.get("/*").order(Integer.MIN_VALUE).handler(interceptor);
    }
}
