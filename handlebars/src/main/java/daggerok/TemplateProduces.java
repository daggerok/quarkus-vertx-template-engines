package daggerok;

import io.vertx.core.Vertx;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.templ.handlebars.HandlebarsTemplateEngine;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@ApplicationScoped
public class TemplateProduces {

    @Produces
    @Dependent
    TemplateEngine engine(Vertx vertx) {
        return HandlebarsTemplateEngine.create(vertx);
    }

    @Produces
    @Singleton
    TemplateHandler templateHandler(TemplateEngine engine) {
        return TemplateHandler.create(engine, "templates", "text/html");
    }
}
