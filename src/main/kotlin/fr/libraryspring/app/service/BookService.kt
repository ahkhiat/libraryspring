package fr.libraryspring.app.service

import fr.libraryspring.app.mapper.BookMapper
import fr.libraryspring.app.repository.AuthorRepository
import fr.libraryspring.app.repository.BookRepository
import fr.libraryspring.app.web.rest.dto.BookDto
import fr.libraryspring.app.web.rest.dto.CreateOrUpdateBook
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository,
    private val bookMapper: BookMapper
){
    @Transactional(readOnly = true)
    fun getBooks(): List<BookDto> = bookRepository.findAll().map {
        bookMapper.toBookDto(it)
    }



    @Transactional
    fun createBook(createOrUpdateBook: CreateOrUpdateBook) {
        val author = createOrUpdateBook.authorId?.let {
            authorRepository.findById(it)
                .orElseThrow { RuntimeException("Author not found") }
        }

        bookRepository.save(
            bookMapper.fromCreateBook(createOrUpdateBook, author)
        )
    }

    @Transactional
    fun updateBook(id: UUID, createOrUpdateBook: CreateOrUpdateBook) {
        val book = bookRepository.findById(id)
            .orElseThrow { RuntimeException("Book not found") }


        book.apply {
            title = createOrUpdateBook.title

            if(
                createOrUpdateBook.authorId != null
                && (author == null || createOrUpdateBook.authorId != author?.id)
            ) {
                val newAuthor = authorRepository.findById(createOrUpdateBook.authorId)
                        .orElseThrow{ RuntimeException("Author not found") }
                author = newAuthor
            }
        }
        bookRepository.save(book)

    }

    @Transactional
    fun deleteBook(id: UUID) {
        val agent = bookRepository.findById(id)
            .orElseThrow { RuntimeException("Book not found") }
        bookRepository.delete(agent)
    }

    @Transactional(readOnly = true)
    fun searchByTitle(title: String): List<BookDto> {
        return bookRepository.findByTitleContaining(title).map {
            bookMapper.toBookDto(it)
        }
    }

}