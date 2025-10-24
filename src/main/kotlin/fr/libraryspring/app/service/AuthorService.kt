package fr.libraryspring.app.service

import fr.libraryspring.app.mapper.AuthorMapper
import fr.libraryspring.app.repository.AuthorRepository
import fr.libraryspring.app.web.rest.dto.AuthorDto
import fr.libraryspring.app.web.rest.dto.CreateOrUpdateAuthor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AuthorService(
    private val authorRepository: AuthorRepository,
    private val authorMapper: AuthorMapper
){
    @Transactional(readOnly = true)
    fun getAuthors(): List<AuthorDto> = authorRepository.findAll().map {
        authorMapper.toAuthorDto(it)
    }

    @Transactional
    fun createAuthor(createOrUpdateAuthor: CreateOrUpdateAuthor) {
        authorRepository.save(
            authorMapper.fromCreateAuthor(createOrUpdateAuthor)
        )
    }

    @Transactional
    fun updateAuthor(id: UUID, createOrUpdateAuthor: CreateOrUpdateAuthor) {
        val author = authorRepository.findById(id)
            .orElseThrow { RuntimeException("Author not found") }

        author.lastname = createOrUpdateAuthor.lastname
        author.firstname = createOrUpdateAuthor.firstname

        authorRepository.save(author)
    }

    @Transactional
    fun deleteAuthor(id: UUID) {
        val author = authorRepository.findById(id)
            .orElseThrow { RuntimeException("Author not found") }
        authorRepository.delete(author)
    }
}