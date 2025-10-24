package fr.libraryspring.app.service

import fr.libraryspring.app.mapper.AuthorMapper
import fr.libraryspring.app.mapper.EditionMapper
import fr.libraryspring.app.repository.AuthorRepository
import fr.libraryspring.app.repository.BookRepository
import fr.libraryspring.app.repository.EditionRepository
import fr.libraryspring.app.web.rest.dto.AuthorDto
import fr.libraryspring.app.web.rest.dto.CreateOrUpdateAuthor
import fr.libraryspring.app.web.rest.dto.CreateOrUpdateEdition
import fr.libraryspring.app.web.rest.dto.EditionDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EditionService(
    private val editionRepository: EditionRepository,
    private val editionMapper: EditionMapper,
    private val bookRepository: BookRepository
) {
    @Transactional(readOnly = true)
    fun getEditions(): List<EditionDto> = editionRepository.findAll().map {
        editionMapper.toEditionDto(it)
    }

    @Transactional
    fun createEdition(createOrUpdateEdition: CreateOrUpdateEdition) {
        val book = bookRepository.findById(
            createOrUpdateEdition.bookId
                ?: throw IllegalArgumentException("Book ID must not be null")
        ).orElseThrow { RuntimeException("Book not found") }

        editionRepository.save(
            editionMapper.fromCreateEdition(createOrUpdateEdition, book)
        )
    }

    @Transactional(readOnly = true)
    fun searchByTitle(title: String): List<EditionDto> {
        return editionRepository.findByBookTitleContaining(title).map {
            editionMapper.toEditionDto(it)
        }
    }

}