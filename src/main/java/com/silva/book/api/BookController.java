package com.silva.book.api;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class BookController extends RouteBuilder {

    @Autowired
    private AppProperties appProperties;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").host("localhost").port(8090).bindingMode(RestBindingMode.json);

        rest().get("/hello").produces(MediaType.APPLICATION_JSON_VALUE)
                .route().setBody(constant("Bienvenido java: "+ appProperties.getMenus())).endRest();


    }

}
