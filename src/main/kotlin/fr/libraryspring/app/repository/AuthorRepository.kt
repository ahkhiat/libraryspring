package fr.libraryspring.app.repository

import fr.libraryspring.app.model.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AuthorRepository: JpaRepository<Author, UUID>