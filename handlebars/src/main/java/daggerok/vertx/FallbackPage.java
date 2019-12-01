package daggerok.vertx;

import daggerok.Handlebars;
import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FallbackPage {

    @Inject
    Logger log;

    @Inject
    Handlebars hbs;

    @Route(regex = "^((?!hello|!index|!about).)*$", order = PageOrder.FALLBACK_RESTEasy)
    void fallback(RoutingContext rc) {
        log.info("Vertx: render fallback");
        hbs.render(rc, "fallback.html.hbs");
    }
}
