package it.chicio.tattoositeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TattooApplication

fun main(args: Array<String>) {
	runApplication<TattooApplication>(*args)
}
