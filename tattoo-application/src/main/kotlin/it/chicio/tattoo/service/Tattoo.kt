package it.chicio.tattoo.service

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Tattoo(
        var id: Long,
        var title: String,
        var description: String,
        var dimensions: Dimensions,
        var style: TattooStyles
)
