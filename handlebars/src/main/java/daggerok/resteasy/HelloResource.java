package daggerok.resteasy;

import io.vertx.core.json.JsonObject;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@ApplicationScoped
public class HelloResource {

    @Inject
    Logger log;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject hello() {
        log.info("RESTEasy hello");
        return new JsonObject().put("message", "hello");
    }
}
