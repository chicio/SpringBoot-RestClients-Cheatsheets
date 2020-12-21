package it.chicio.tattoo.configuration

import it.chicio.tattoo.TattooRestService
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
    fun tattooRestService(
            restTemplate: RestTemplate,
            tattooServiceConfiguration: TattooServiceConfiguration
    ): TattooRestService = TattooRestService(tattooServiceConfiguration, restTemplate)
}