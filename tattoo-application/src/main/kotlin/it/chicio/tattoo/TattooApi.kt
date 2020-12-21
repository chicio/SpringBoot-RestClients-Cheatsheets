@file:Suppress("IMPLICIT_CAST_TO_ANY")

package it.chicio.tattoo

import it.chicio.tattoo.Command.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tattoo")
class TattooApi(
    val tattooRestService: TattooRestService
) {
    @GetMapping("/command/{command}")
    fun command(@PathVariable command: Command): ResponseEntity<String> = ResponseEntity.ok(when (command) {
        GetForEntity -> tattooRestService.getForEntity()
        GetForObject -> tattooRestService.getForObject()
        PostForEntity -> tattooRestService.postForEntity()
        PostForObject -> tattooRestService.postForObject()
        Put -> tattooRestService.put()
        Delete -> tattooRestService.delete()
        Exchange -> tattooRestService.exchange()
    }.toString())
}
