package com.sunilkata.template.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.sunilkata.template.model.ModelObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Locale;
import java.util.Optional;

/**
 * Created by Sunil Kata on 7/30/2017.
 */
@Path("/template")
@Produces(MediaType.APPLICATION_JSON)
public class TemplateResource {

    @GET
    @Timed
    public Response sayHello(@QueryParam("name") Optional<String> name) {
        ModelObject o = new ModelObject("Test STring");
        return Response.status(Response.Status.OK).entity(o).language(Locale.UK).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
