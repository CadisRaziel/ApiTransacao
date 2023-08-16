package com.picpaysimplified.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

///Configuração do restTemplate
@Configuration
public class AppConfig {
    @Bean ///Realizando a instancia o Bean é capaz de injetar esse cofiguration nas classes que esta usando o RestTemplate
    public RestTemplate restTemplate() {
        ///Criando a instancia do restTemplate e retornando
        return new RestTemplate();
    }
}
