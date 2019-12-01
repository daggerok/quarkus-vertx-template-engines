package daggerok.resteasy;

import io.vertx.core.json.JsonObject;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@ApplicationScoped
public class FallbackResource {

    @Inject
    Logger log;

    @GET
    @Path("{path: .+$}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject fallback(@PathParam("path") String path) {
        String error = String.format("RESTEasy fallback: %s", path);
        log.info(error);
        return new JsonObject().put("error", error);
    }
}
