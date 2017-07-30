package com.sunilkata.template.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Created by Sunil Kata on 7/30/2017.
 */

@Path("keepalive")
@Produces(MediaType.APPLICATION_JSON)
@Api("keepalive")
public class KeepAliveResource {

    public KeepAliveResource() {
    }

    @GET
    @ApiOperation(value = "Keepalive API", notes = "Healthcheck keepalive api", response = KeepAliveResource.class)
    public Response getKeepAlive() {
        return  Response.status(200).build();
    }

}

