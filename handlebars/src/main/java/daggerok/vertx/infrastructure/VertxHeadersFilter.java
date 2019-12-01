package daggerok.vertx.infrastructure;

import io.quarkus.vertx.http.runtime.filters.Filters;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class VertxHeadersFilter {

    private static final String HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"))
                                                   .orElse(UUID.randomUUID().toString());

    void configure(@Observes Filters filters) {
        filters.register(this::addCustomHeaders, Integer.MAX_VALUE - 1);
    }

    private void addCustomHeaders(RoutingContext rc) {
        HttpServerRequest request = rc.request();
        rc.response()
          .putHeader("X-PATH", request.path())
          .putHeader("X-URL", request.absoluteURI())
          .putHeader("X-HOSTNAME", HOSTNAME);
        rc.next();
    }
}
