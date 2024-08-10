package com.suleimanov.manager.config;

import com.suleimanov.manager.client.ResttClientProductsRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public ResttClientProductsRestClient productsRestClient(@Value("${selmag.services.catalogue.uri:$:http://localhost:8081}") String catalogueBaseUri) {
        return new ResttClientProductsRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .build());
    }
}
