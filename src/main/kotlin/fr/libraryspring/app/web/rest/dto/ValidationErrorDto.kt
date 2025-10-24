package fr.libraryspring.app.web.rest.dto

data class ValidationError(
    val field: String,
    val message: String
)

data class ValidationErrorResponse(
    val errors: List<ValidationError>
)