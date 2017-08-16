package com.sunilkata.template.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.inject.name.Named;
import com.sunilkata.template.model.ModelObject;
import com.sunilkata.template.repository.TemplateRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Locale;
import java.util.Optional;

import static javax.ws.rs.core.Response.ok;

/**
 * Created by Sunil Kata on 7/30/2017.
 */
@Path("/template")
@Produces(MediaType.APPLICATION_JSON)
public class TemplateResource {

    @Inject
    @Named("templateRepository")
    private TemplateRepository templateRepository;

    @POST
    public Response setTestId(@QueryParam("testId") String testId) {
        templateRepository.insertTestId(testId);
        return Response.status(Response.Status.OK).language(Locale.UK).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}
