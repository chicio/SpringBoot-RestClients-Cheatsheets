@file:Suppress("IMPLICIT_CAST_TO_ANY")

package it.chicio.tattoo

import it.chicio.tattoo.service.RestTemplateCommand
import it.chicio.tattoo.service.TattooRestTemplateService
import it.chicio.tattoo.service.TattooWebClientService
import it.chicio.tattoo.service.WebClientCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("rest")
class TattooApi(
    private val tattooRestTemplateService: TattooRestTemplateService,
    private val tattooWebClientService: TattooWebClientService
) {
    @GetMapping("/rest-template/command/{restTemplateCommand}")
    fun commandRestTemplate(@PathVariable restTemplateCommand: RestTemplateCommand): ResponseEntity<String> = ResponseEntity.ok(when (restTemplateCommand) {
        RestTemplateCommand.GetForEntity -> tattooRestTemplateService.getForEntity()
        RestTemplateCommand.GetForObject -> tattooRestTemplateService.getForObject()
        RestTemplateCommand.PostForEntity -> tattooRestTemplateService.postForEntity()
        RestTemplateCommand.PostForObject -> tattooRestTemplateService.postForObject()
        RestTemplateCommand.Put -> tattooRestTemplateService.put()
        RestTemplateCommand.Delete -> tattooRestTemplateService.delete()
        RestTemplateCommand.Exchange -> tattooRestTemplateService.exchange()
    }.toString())

    @GetMapping("/webclient/command/{webClientCommand}")
    fun command(@PathVariable webClientCommand: WebClientCommand): ResponseEntity<String> = ResponseEntity.ok(when (webClientCommand) {
        WebClientCommand.GetSynchronous -> tattooWebClientService.getSynchronous()
    }.toString())
}
