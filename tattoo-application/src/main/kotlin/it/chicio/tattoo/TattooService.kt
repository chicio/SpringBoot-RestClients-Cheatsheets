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
                UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri(),
                Tattoo::class.java
        )
    } catch (e: RestClientResponseException) {
        ResponseEntity.status(e.rawStatusCode).build()
    }

    fun getForObject(): Tattoo? = try {
        restTemplate.getForObject(
                UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri(),
                Tattoo::class.java
        )
    } catch (e: RestClientResponseException) {
        null
    }

    fun postForEntity(): ResponseEntity<TattooPostResult> = try {
        restTemplate.postForEntity(
                UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri(),
                Tattoo(123, "A new beautiful tattoo", "My latest new school tattoo on my left leg", Dimensions(100, 40), TattooStyles.NewSchool),
                TattooPostResult::class.java
        )
    } catch (e: RestClientResponseException) {
        ResponseEntity.status(e.rawStatusCode).build()
    }

    fun postForObject(): TattooPostResult? = try {
        restTemplate.postForObject(
                UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri(),
                Tattoo(123, "A new beautiful tattoo", "My latest new school tattoo on my left leg", Dimensions(100, 40), TattooStyles.NewSchool),
                TattooPostResult::class.java
        )
    } catch (e: RestClientResponseException) {
        null
    }
}