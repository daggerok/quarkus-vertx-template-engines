package daggerok.vertx;

import daggerok.Handlebars;
import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class IndexPage {

    @Inject
    Logger log;

    @Inject
    Handlebars hbs;

    @Route(path = "/")
    void index(RoutingContext rc) {
        log.info("Vertx: render home");
        hbs.render(rc, "index.html.hbs");
    }
}
