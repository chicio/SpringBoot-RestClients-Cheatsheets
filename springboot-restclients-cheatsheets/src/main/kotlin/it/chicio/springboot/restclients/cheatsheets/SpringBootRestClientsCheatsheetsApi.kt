@file:Suppress("IMPLICIT_CAST_TO_ANY")

package it.chicio.springboot.restclients.cheatsheets

import it.chicio.springboot.restclients.cheatsheets.service.RestTemplateCommand
import it.chicio.springboot.restclients.cheatsheets.service.RestTemplateService
import it.chicio.springboot.restclients.cheatsheets.service.WebClientService
import it.chicio.springboot.restclients.cheatsheets.service.WebClientCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("clients")
class SpringBootRestClientsCheatsheetsApi(
        private val restTemplateService: RestTemplateService,
        private val webClientService: WebClientService
) {
    @GetMapping("/rest-template/command/{restTemplateCommand}")
    fun commandRestTemplate(@PathVariable restTemplateCommand: RestTemplateCommand): ResponseEntity<String> = ResponseEntity.ok(when (restTemplateCommand) {
        RestTemplateCommand.GetForEntity -> restTemplateService.getForEntity()
        RestTemplateCommand.GetForObject -> restTemplateService.getForObject()
        RestTemplateCommand.PostForEntity -> restTemplateService.postForEntity()
        RestTemplateCommand.PostForObject -> restTemplateService.postForObject()
        RestTemplateCommand.Put -> restTemplateService.put()
        RestTemplateCommand.Delete -> restTemplateService.delete()
        RestTemplateCommand.Exchange -> restTemplateService.exchange()
    }.toString())

    @GetMapping("/webclient/command/{webClientCommand}")
    fun command(@PathVariable webClientCommand: WebClientCommand): ResponseEntity<String> = ResponseEntity.ok(when (webClientCommand) {
        WebClientCommand.GetSynchronous -> webClientService.getSynchronous()
    }.toString())
}
