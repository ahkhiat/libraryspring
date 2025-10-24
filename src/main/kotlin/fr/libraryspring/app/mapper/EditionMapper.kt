package fr.libraryspring.app.mapper

import fr.libraryspring.app.model.Author
import fr.libraryspring.app.model.Book
import fr.libraryspring.app.model.Edition
import fr.libraryspring.app.web.rest.dto.EditionDto
import fr.libraryspring.app.web.rest.dto.CreateOrUpdateEdition
import org.springframework.stereotype.Component


@Component
class EditionMapper(
) {
    fun toEditionDto(edition: Edition) = EditionDto(
        id = edition.id,
        isbn = edition.isbn,
        publisher = edition.publisher,
        publicationYear = edition.publicationYear,
    )

    fun fromCreateEdition(createOrUpdateEdition: CreateOrUpdateEdition, book: Book) = Edition(
        isbn = createOrUpdateEdition.isbn,
        publisher = createOrUpdateEdition.publisher,
        publicationYear = createOrUpdateEdition.publicationYear,
        book = book
    )

}