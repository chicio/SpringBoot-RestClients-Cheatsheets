package it.chicio.tattoo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "tattooservice")
class TattooServiceConfiguration {
    var url: String = ""
}