package it.chicio.springboot.restclients.cheatsheets.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Tattoo(
        var id: Long,
        var title: String,
        var dimensions: Dimensions,
        var style: TattooStyles
)
