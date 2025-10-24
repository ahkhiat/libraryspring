package fr.libraryspring.app.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name= "author")
data class Author(
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    var id: UUID? = null,
    var firstname: String,
    var lastname: String,

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    val books: MutableList<Book> = mutableListOf()
)