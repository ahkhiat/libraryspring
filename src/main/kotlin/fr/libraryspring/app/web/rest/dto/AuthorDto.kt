package fr.libraryspring.app.web.rest.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.util.*

interface CreateAuthorGroup

data class AuthorDto(
    val id: UUID? = null,
    val firstname: String,
    val lastname: String
)

data class CreateOrUpdateAuthor(

    @field: NotBlank(groups = [CreateAuthorGroup::class], message = "Le prénom est obligatoire")
    @field:Size(min = 2,groups = [CreateAuthorGroup::class], message = "Le prénom doit contenir au minimum 2 caractères")
    val firstname: String,

    @field: NotBlank(groups = [CreateAuthorGroup::class], message = "Le nom de famille est obligatoire")
    @field:Size(min = 2,groups = [CreateAuthorGroup::class], message = "Le nom doit contenir au minimum 2 caractères")
    val lastname: String
)

