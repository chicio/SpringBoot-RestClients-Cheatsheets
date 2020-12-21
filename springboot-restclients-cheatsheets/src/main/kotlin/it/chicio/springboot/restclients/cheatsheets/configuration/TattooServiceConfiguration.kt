package it.chicio.springboot.restclients.cheatsheets.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "tattooservice")
class TattooServiceConfiguration {
    var url: String = ""
}