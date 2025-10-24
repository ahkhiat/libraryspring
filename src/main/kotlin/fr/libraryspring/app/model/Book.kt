package fr.libraryspring.app.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.util.*

@Entity
@NamedEntityGraph(
    name = "Book.withAuthorAndEditions",
    attributeNodes = [
        NamedAttributeNode("author"),
        NamedAttributeNode("editions")
    ]
)
@Table(name= "book")
data class Book(
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    var id: UUID? = null,
    var title: String,

    @ManyToOne
    var author: Author?,

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @JsonManagedReference
    var editions: MutableList<Edition> = mutableListOf()
)