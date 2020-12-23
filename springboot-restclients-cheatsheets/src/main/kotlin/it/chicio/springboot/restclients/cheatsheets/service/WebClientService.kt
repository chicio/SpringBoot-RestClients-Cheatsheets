package it.chicio.springboot.restclients.cheatsheets.service

import it.chicio.springboot.restclients.cheatsheets.configuration.TattooServiceConfiguration
import it.chicio.springboot.restclients.cheatsheets.json.Dimensions
import it.chicio.springboot.restclients.cheatsheets.json.Tattoo
import it.chicio.springboot.restclients.cheatsheets.json.TattooPostResult
import it.chicio.springboot.restclients.cheatsheets.json.TattooStyles
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

class WebClientService(
        private val tattooServiceConfiguration: TattooServiceConfiguration,
        private val webClient: WebClient
) {
    fun getSynchronous(): ResponseEntity<Tattoo>? = webClient
            .get()
            .uri(UriComponentsBuilder
                    .fromHttpUrl(tattooServiceConfiguration.url)
                    .path("/tattoo/123")
                    .build()
                    .toUri())
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .toEntity(Tattoo::class.java)
            .block()

    fun getAsynchronous(): Mono<Tattoo> = webClient
            .get()
            .uri(UriComponentsBuilder
                    .fromHttpUrl(tattooServiceConfiguration.url)
                    .path("/tattoo/123")
                    .build()
                    .toUri())
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(Tattoo::class.java)

    fun postSynchronous(): ResponseEntity<TattooPostResult>? = webClient
            .post()
            .uri(UriComponentsBuilder
                    .fromHttpUrl(tattooServiceConfiguration.url)
                    .path("/tattoo/123")
                    .build()
                    .toUri())
            .body(BodyInserters.fromValue(Tattoo(123, "A new beautiful tattoo", Dimensions(100, 40), TattooStyles.NewSchool)))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .toEntity(TattooPostResult::class.java)
            .block()

    fun postAsynchronous(): Mono<TattooPostResult> = webClient
            .post()
            .uri(UriComponentsBuilder
                    .fromHttpUrl(tattooServiceConfiguration.url)
                    .path("/tattoo/123")
                    .build()
                    .toUri())
            .body(BodyInserters.fromValue(Tattoo(123, "A new beautiful tattoo", Dimensions(100, 40), TattooStyles.NewSchool)))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
            .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
            .bodyToMono(TattooPostResult::class.java)



    fun putSynchronous(): String {
        val tattoo = Tattoo(123, "A new beautiful tattoo", Dimensions(100, 40), TattooStyles.NewSchool)
        webClient
                .put()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri())
                .body(BodyInserters.fromValue(tattoo))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
                .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
                .bodyToMono(Void::class.java)
                .block()
        return "Tattoo resource created $tattoo"
    }

    fun deleteSynchronous(): String {
        webClient
                .put()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError) { Mono.error(RuntimeException("4XX Error ${it.statusCode()}")) }
                .onStatus(HttpStatus::is5xxServerError) { Mono.error(RuntimeException("5XX Error ${it.statusCode()}")) }
                .bodyToMono(Void::class.java)
                .block()
        return "Tattoo resource deleted"
    }
}