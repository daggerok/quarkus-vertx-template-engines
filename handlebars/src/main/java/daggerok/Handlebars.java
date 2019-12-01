package daggerok;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.common.template.TemplateEngine;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;

import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE;
import static io.vertx.core.http.HttpHeaders.TEXT_HTML;

/**
 * This is a main template engine renderer
 *
 * <pre>
 *     @Inject
 *     Handlebars hbs;
 *     // ...
 *     hbs.render(rc, "index.hbs", "message", "Hello, World!");
 * </pre>
 */
@Singleton
public class Handlebars {

    @Inject
    TemplateEngine engine;

    public void render(RoutingContext rc, String template, Object... kv) {
        if (Objects.isNull(kv) || kv.length % 2 != 0)
            throw new RuntimeException("Context key-values args amount should should be % 2");

        JsonObject context = new JsonObject();
        for (int i = 0; i < kv.length; ) {
            String key = (String) kv[i++];
            Object value = kv[i++];
            context = context.put(key, value);
        }

        render(rc, template, context);
    }

    public void render(RoutingContext rc, String template, JsonObject context) {
        engine.render(context, String.format("templates/%s", template), event -> {
            if (event.failed()) {
                rc.response()
                  .setStatusCode(400)
                  .putHeader(CONTENT_TYPE, "application/json")
                  .end(event.cause().getLocalizedMessage());
                return;
            }
            rc.response()
              .putHeader(CONTENT_TYPE, TEXT_HTML)
              .end(event.result());
        });
    }
}
