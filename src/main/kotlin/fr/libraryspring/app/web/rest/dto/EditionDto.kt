package fr.libraryspring.app.web.rest.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*

interface CreateEditionGroup

data class EditionDto(
    val id: UUID? = null,
    val isbn: String,
    val publisher: String,
    val publicationYear: Int,
)

data class CreateOrUpdateEdition(
    @field: NotBlank(groups = [CreateEditionGroup::class], message = "L'ISBN est obligatoire")
    @field:Size(min = 9,groups = [CreateEditionGroup::class], message = "L'ISBN doit contenir au minimum 9 caractères")
    val isbn: String,

    @field: NotBlank(groups = [CreateEditionGroup::class], message = "L'éditeur est obligatoire")
    @field:Size(min = 2,groups = [CreateEditionGroup::class], message = "L'éditeur doit contenir au minimum 2 caractères")
    val publisher: String,

    val publicationYear: Int,
    val bookId: UUID?
)