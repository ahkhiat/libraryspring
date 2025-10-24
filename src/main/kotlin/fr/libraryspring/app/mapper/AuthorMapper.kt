package fr.libraryspring.app.mapper

import fr.libraryspring.app.model.Author
import fr.libraryspring.app.web.rest.dto.AuthorDto
import fr.libraryspring.app.web.rest.dto.CreateOrUpdateAuthor
import org.springframework.stereotype.Component


@Component
class AuthorMapper {
    fun toAuthorDto(author: Author) = AuthorDto(
        id = author.id,
        firstname = author.firstname,
        lastname = author.lastname
    )

    fun fromCreateAuthor(createOrUpdateAuthor: CreateOrUpdateAuthor) = Author(
        firstname = createOrUpdateAuthor.firstname,
        lastname = createOrUpdateAuthor.lastname
    )

}