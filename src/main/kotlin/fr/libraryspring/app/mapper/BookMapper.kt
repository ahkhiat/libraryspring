package fr.libraryspring.app.mapper

import fr.libraryspring.app.model.Author
import fr.libraryspring.app.model.Book
import fr.libraryspring.app.web.rest.dto.AuthorDto
import fr.libraryspring.app.web.rest.dto.BookDto
import fr.libraryspring.app.web.rest.dto.CreateOrUpdateAuthor
import fr.libraryspring.app.web.rest.dto.CreateOrUpdateBook
import org.springframework.stereotype.Component

@Component
class BookMapper(
    private val authorMapper: AuthorMapper,
    private val editionMapper: EditionMapper
) {
    fun toBookDto(book: Book) = BookDto(
        id = book.id,
        title = book.title,
        author = book.author?.let {
            authorMapper.toAuthorDto(it)
        },
        editions = book.editions.map {
            editionMapper.toEditionDto(it)
        }
    )

    fun fromCreateBook(createOrUpdateBook: CreateOrUpdateBook, author: Author?) = Book(
        title = createOrUpdateBook.title,
        author = author
    )
}