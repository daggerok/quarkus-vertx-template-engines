package daggerok.vertx;

import daggerok.Handlebars;
import io.vertx.ext.web.Router;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class AboutPageProgrammatic {

    @Inject
    Logger log;

    @Inject
    Handlebars hbs;

    void init(@Observes Router router) {
        router.get("/about*")
              .order(PageOrder.ABOUT) // disabled when commented, otherwise enabled
              .handler(rc -> {
                  log.infof("Vertx: programmatic about view %s", rc.request().path());
                  hbs.render(rc, "about.html.hbs", "kind-of", "programmatic");
              });
    }
}
