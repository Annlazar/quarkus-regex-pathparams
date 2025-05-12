package org.acme.rest.client;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
public class ExtensionsResourceTest {

    @Test
    public void working() {

        given()
            .when().get("/test/params/greetings/hello/world")
            .then()
            .statusCode(200)
            .body(is("op: greetings, left: hello, right: world"));
    }

    @Test
    public void not_working() {

        given()
            .when().get("/test/params/greetings/hello/")
            .then()
            .statusCode(200)
            .body(is("op: greetings, left: hello, right: "));
    }
}
