package it.chicio.springboot.restclients.cheatsheets.configuration

import it.chicio.springboot.restclients.cheatsheets.service.RestTemplateService
import it.chicio.springboot.restclients.cheatsheets.service.WebClientService
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class SpringBootRestClientsCheatsheetsConfiguration {
    @Bean
    fun restTemplate(): RestTemplate =  RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(10))
            .build()

    @Bean
    fun restTemplateService(
            restTemplate: RestTemplate,
            tattooServiceConfiguration: TattooServiceConfiguration
    ): RestTemplateService = RestTemplateService(tattooServiceConfiguration, restTemplate)

    @Bean
    fun tattooWebClientService(
            tattooServiceConfiguration: TattooServiceConfiguration
    ): WebClientService = WebClientService(tattooServiceConfiguration)
}