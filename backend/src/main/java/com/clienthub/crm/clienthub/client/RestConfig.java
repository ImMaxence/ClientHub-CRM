package com.clienthub.crm.clienthub.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

  /**
   * Déclare un bean RestTemplate, utilisable via @Autowired ou
   * injection constructeur (ex : dans FastApiClient).
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
