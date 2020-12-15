package it.chicio.tattoo

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
        Command.GetForEntity -> tattooRestService.getForEntity()
        Command.PostForEntity -> tattooRestService.postForEntity()
    }.toString())
}
