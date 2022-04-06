package com.sea.springcloud.gateway.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Nonnull;

@Component
@RequiredArgsConstructor
public class SwaggerResourcesHandler implements HandlerFunction<ServerResponse> {
    private final SwaggerResourcesProvider swaggerResourcesProvider;

    @Override
    @Nonnull
    public Mono<ServerResponse> handle(@Nonnull ServerRequest serverRequest) {
        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(swaggerResourcesProvider.get()));
    }
}
