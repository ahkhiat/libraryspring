package fr.libraryspring.app.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name= "edition")
data class Edition(
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    var id: UUID? = null,
    var isbn: String,
    var publisher: String,
    var publicationYear: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonBackReference
    var book: Book
)