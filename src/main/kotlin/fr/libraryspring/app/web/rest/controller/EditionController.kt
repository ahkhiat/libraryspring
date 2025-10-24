package fr.libraryspring.app.web.rest.controller

import fr.libraryspring.app.mapper.EditionMapper
import fr.libraryspring.app.service.EditionService
import fr.libraryspring.app.web.rest.dto.BookDto
import fr.libraryspring.app.web.rest.dto.EditionDto
import fr.libraryspring.app.web.rest.dto.CreateEditionGroup
import fr.libraryspring.app.web.rest.dto.CreateOrUpdateEdition
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/edition")
class EditionController(
    private val editionService: EditionService,
    private val editionMapper: EditionMapper
) {
    @GetMapping
    fun getEditions(): List<EditionDto> = editionService.getEditions()

    @PostMapping
    fun createEdition(
        @Validated(CreateEditionGroup::class)
        @RequestBody createOrUpdateEdition: CreateOrUpdateEdition
    ) {
        editionService.createEdition(createOrUpdateEdition)
    }

    @GetMapping("/search")
    fun searchByTitle(
        @RequestParam("title") title: String
    ) : ResponseEntity<List<EditionDto>> {
        return ResponseEntity.ok(editionService.searchByTitle(title))
    }
}