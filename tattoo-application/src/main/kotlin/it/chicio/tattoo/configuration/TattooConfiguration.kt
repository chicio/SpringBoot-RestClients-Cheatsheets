package it.chicio.tattoo.configuration

import it.chicio.tattoo.service.TattooRestTemplateService
import it.chicio.tattoo.service.TattooWebClientService
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class TattooConfiguration {
    @Bean
    fun restTemplate(): RestTemplate =  RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(10))
            .build()

    @Bean
    fun tattooRestTemplateService(
            restTemplate: RestTemplate,
            tattooServiceConfiguration: TattooServiceConfiguration
    ): TattooRestTemplateService = TattooRestTemplateService(tattooServiceConfiguration, restTemplate)

    @Bean
    fun tattooWebClientService(
            tattooServiceConfiguration: TattooServiceConfiguration
    ): TattooWebClientService = TattooWebClientService(tattooServiceConfiguration)
}