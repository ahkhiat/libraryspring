package fr.libraryspring.app.web.rest.controller

import fr.libraryspring.app.mapper.BookMapper
import fr.libraryspring.app.service.BookService
import fr.libraryspring.app.web.rest.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/book")
class BookController(
    private val bookService: BookService,
    private val bookMapper: BookMapper
){
    @GetMapping
    fun getBooks(): List<BookDto> = bookService.getBooks()

    @PostMapping
    fun createBook(
        @Validated(CreateBookGroup::class)
        @RequestBody createOrUpdateBook: CreateOrUpdateBook
    ) {
        bookService.createBook(createOrUpdateBook)
    }

    @PutMapping("/{id}")
    fun updateBook(
        @Validated(CreateBookGroup::class)
        @PathVariable id: UUID,
        @RequestBody createOrUpdateBook: CreateOrUpdateBook
    ) {
        bookService.updateBook(id, createOrUpdateBook)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(
        @PathVariable id: UUID
    ){
        bookService.deleteBook(id)
    }

    @GetMapping("/search")
    fun searchByTitle(
        @RequestParam("title") title: String
    ) : ResponseEntity<List<BookDto>>{
        return ResponseEntity.ok(bookService.searchByTitle(title))
    }


}