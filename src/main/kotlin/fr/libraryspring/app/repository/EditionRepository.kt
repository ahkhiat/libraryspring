package fr.libraryspring.app.repository

import fr.libraryspring.app.model.Edition
import fr.libraryspring.app.web.rest.dto.EditionDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EditionRepository: JpaRepository<Edition, UUID> {
    fun findByBookTitleContaining(title: String): List<Edition>
}