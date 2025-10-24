package fr.libraryspring.app.web.rest.controller

import fr.libraryspring.app.mapper.AuthorMapper
import fr.libraryspring.app.service.AuthorService
import fr.libraryspring.app.web.rest.dto.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/author")
class AuthorController(
    private val authorService: AuthorService,
    private val authorMapper: AuthorMapper
){
    @GetMapping
    fun getAuthors(): List<AuthorDto> = authorService.getAuthors()

    @PostMapping
    fun createAuthor(
        @Validated(CreateAuthorGroup::class)
        @RequestBody createOrUpdateAuthor: CreateOrUpdateAuthor
    ) {
        authorService.createAuthor(createOrUpdateAuthor)
    }

    @PutMapping("/{id}")
    fun updateAuthor(
        @Validated(CreateAuthorGroup::class)
        @PathVariable id: UUID,
        @RequestBody createOrUpdateAuthor: CreateOrUpdateAuthor
    ) {
        authorService.updateAuthor(id, createOrUpdateAuthor)
    }

    @DeleteMapping("/{id}")
    fun deleteAuthor(
        @PathVariable id: UUID
    ){
        authorService.deleteAuthor(id)
    }


}