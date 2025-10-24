package fr.libraryspring.app.web.rest.dto

import jakarta.validation.constraints.*
import java.util.*

interface CreateBookGroup

data class BookDto(
    val id: UUID? = null,
    val title: String,
    val author: AuthorDto?,
    val editions: List<EditionDto>
)

data class CreateOrUpdateBook(

    @field: NotBlank(groups = [CreateBookGroup::class], message = "Le titre est obligatoire")
    @field:Size(min = 2,groups = [CreateBookGroup::class], message = "Le titre doit contenir au minimum 2 caractères")
    val title: String,

    val authorId: UUID?
)

