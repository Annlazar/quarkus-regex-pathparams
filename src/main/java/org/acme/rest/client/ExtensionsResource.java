package org.acme.rest.client;

import jakarta.ws.rs.PathParam;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/test")
public class ExtensionsResource {

    @GET
    @Path("/params/{op}{left : (/[^/]*)?}{right : (/[^/]*?)}")
    public String id(@PathParam("op") String op, @PathParam("left") String left, @PathParam("right") String right) {
        return "op: " + op + ", left: " + left.replace("/", "") + ", right: " + right.replace("/","");
    }

}
