@file:Suppress("DEPRECATION")

package it.chicio.tattoo.service

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import it.chicio.tattoo.configuration.TattooServiceConfiguration
import org.springframework.http.ResponseEntity
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.TcpClient
import java.util.concurrent.TimeUnit

class TattooWebClientService(
        private val tattooServiceConfiguration: TattooServiceConfiguration
) {

    fun getSynchronous(): ResponseEntity<Tattoo>? {
        val tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .doOnConnected { connection: Connection ->
                    connection.addHandlerLast(ReadTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                    connection.addHandlerLast(WriteTimeoutHandler(10000, TimeUnit.MILLISECONDS))
                }

        val webClient = WebClient.builder()
                .clientConnector(ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build()

        return webClient
                .get()
                .uri(UriComponentsBuilder
                        .fromHttpUrl(tattooServiceConfiguration.url)
                        .path("/tattoo/123")
                        .build()
                        .toUri())
                .retrieve()
                .toEntity(Tattoo::class.java)
                .block()
    }
}