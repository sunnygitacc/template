package com.sunilkata.template.resources;

import com.sunilkata.template.api.Saying;
import com.codahale.metrics.annotation.Timed;
import javafx.application.Application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

/**
 * Created by Sunil Kata on 7/30/2017.
 */
@Path("/template")
@Produces(MediaType.APPLICATION_JSON)
public class TemplateResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public TemplateResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
