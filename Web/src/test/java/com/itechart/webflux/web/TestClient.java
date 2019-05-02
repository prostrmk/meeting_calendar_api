package com.itechart.webflux.web;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class TestClient {

    @Test
    public void test() {
        WebClient client = WebClient.create("http://localhost:8080");
        Mono<ClientResponse> result = client.get()
                .uri("/test")
                .accept(MediaType.ALL)
                .exchange();
        String resp = result.flatMap(clientResponse -> clientResponse.bodyToMono(String.class)).block();

        Assert.assertEquals("test", resp);

    }

}
