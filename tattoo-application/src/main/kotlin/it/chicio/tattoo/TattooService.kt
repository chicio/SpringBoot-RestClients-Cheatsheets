package it.chicio.tattoo

import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class TattooRestService(
    private val tattooServiceConfiguration: TattooServiceConfiguration,
    private val restTemplate: RestTemplate
) {
    fun getForEntity(): ResponseEntity<Tattoo> = try {
        restTemplate.getForEntity(
                UriComponentsBuilder.fromHttpUrl(tattooServiceConfiguration.url).path("/tattoo/123").build().toUri(),
                Tattoo::class.java
        )
    } catch (e: RestClientResponseException) {
        ResponseEntity.status(e.rawStatusCode).build()
    }

    fun postForEntity(): ResponseEntity<Tattoo> = TODO("To be implemented")
}