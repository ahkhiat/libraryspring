package fr.libraryspring.app.repository

import fr.libraryspring.app.model.Book
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BookRepository: JpaRepository<Book, UUID> {
    fun findByTitleContaining(title: String): List<Book>

    @EntityGraph(value = "Book.withAuthorAndEditions", type = EntityGraph.EntityGraphType.LOAD)
    override fun findAll(): List<Book>
}