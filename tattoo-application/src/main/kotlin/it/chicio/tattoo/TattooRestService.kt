package it.chicio.tattoo

import it.chicio.tattoo.configuration.TattooServiceConfiguration
import it.chicio.tattoo.json.Dimensions
import it.chicio.tattoo.json.Tattoo
import it.chicio.tattoo.json.TattooStyles
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class TattooRestService(
        private val tattooServiceConfiguration: TattooServiceConfiguration,
        private val restTemplate: RestTemplate
) {
    private var logger = LoggerFactory.getLogger(TattooRestService::class.java)

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

    fun put(): String = try {
        val tattoo = Tattoo(123, "A new beautiful tattoo", "My latest new school tattoo on my left leg", Dimensions(100, 40), TattooStyles.NewSchool)
        restTemplate.put(
                UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri(),
                tattoo,
        )
        "Tattoo resource created $tattoo"
    } catch (e: RestClientResponseException) {
        val error = "Put client error ${e.rawStatusCode}"
        logger.error(error)
        error
    }

    fun delete(): String = try {
        restTemplate.delete(
                UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri()
        )
        "Tattoo resource deleted"
    } catch (e: RestClientResponseException) {
        val error = "Put client error ${e.rawStatusCode}"
        logger.error(error)
        error
    }

    fun exchange(): ResponseEntity<TattooPostResult> = try {
        restTemplate.exchange(
                UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri(),
                HttpMethod.POST,
                HttpEntity<Tattoo>(Tattoo(123, "A new beautiful tattoo", "My latest new school tattoo on my left leg", Dimensions(100, 40), TattooStyles.NewSchool)),
                TattooPostResult::class.java
        )
    } catch (e: RestClientResponseException) {
        ResponseEntity.status(e.rawStatusCode).build()
    }
}