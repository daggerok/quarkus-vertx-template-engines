package daggerok.vertx.infrastructure;

import daggerok.vertx.PageOrder;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.TemplateHandler;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class VertxTemplateHandlerConfig {

    @Inject
    TemplateHandler templateHandler;

    void configure(@Observes Router router) {
        router.routeWithRegex("/.+\\.(html|html.hbs)$")
              .order(PageOrder.TEMPLATE_HANDLER)
              .handler(templateHandler);
    }
}
