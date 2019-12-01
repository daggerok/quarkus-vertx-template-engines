package daggerok.vertx;

import daggerok.Handlebars;
import io.quarkus.vertx.web.Route;
import io.vertx.ext.web.RoutingContext;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AboutPage {

    @Inject
    Logger log;

    @Inject
    Handlebars hbs;

    // @Route.Routes({
    //         @Route(path = "/about*"),
    // })
    // for some reasons, 'declarative\ with same priority is less prioritized
    // than 'programmatically'... see: AboutPageProgrammatic.java
    @Route(path = "/about*", order = PageOrder.ABOUT)
    void render(RoutingContext rc) {
        log.infof("Vertx: declarative about view %s", rc.request().path());
        hbs.render(rc, "about.html.hbs", "kind-of", "declarative");
    }
}
