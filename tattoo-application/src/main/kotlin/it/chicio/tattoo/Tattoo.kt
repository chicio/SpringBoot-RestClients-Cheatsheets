package it.chicio.tattoo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Tattoo(
        var id: Long,
        var title: String,
        var description: String,
        var dimensions: Dimensions,
        var style: TattooStyles
)

enum class TattooStyles {
    NewSchool,
    OldSchool,
    Tribal,
    Japanese
}

data class Dimensions (
        val height: Long,
        val width: Long
)