package br.com.zup.book

import br.com.zup.BookDetailResponse
import br.com.zup.NewAutorResponse
import br.com.zup.NewBookResponse
import br.com.zup.NewCategoryResponse
import br.com.zup.autor.Autor
import br.com.zup.catergoria.Category
import br.com.zup.shared.toTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.*

@Entity
class Book(
    @field:NotBlank
    @Column(nullable = false, unique = true)
    val title: String,

    @field:NotBlank
    @field:Size(max = 500)
    @Column(nullable = false)
    val abstracts: String,

    val summary: String,

    @field:NotNull
    @field:Min(20)
    @Column(nullable = false)
    val price: BigDecimal,

    @field:NotNull
    @field:Min(100)
    @Column(nullable = false)
    val pagesNumber: Int,

    @field:NotBlank
    @Column(nullable = false, unique = true)
    val isbn: String,

    @field:Future
    @Column(nullable = false)
    val inicialDates: LocalDateTime,

    @ManyToOne
    @field:Valid
    val category: Category,

    @ManyToOne
    @field:Valid
    val autor: Autor
) {
    @Id
    @GeneratedValue
    var id: UUID? = null

    fun converts(): NewBookResponse {
        return NewBookResponse.newBuilder()
            .setBookId(id.toString())
            .setTitle(title)
            .setAbstract(abstracts)
            .setSummary(summary)
            .setPrice(price.toDouble())
            .setPagesNumber(pagesNumber)
            .setIsbn(isbn)
            .setInicialDates(inicialDates.toTimestamp())
            .setCategory(
                NewCategoryResponse.newBuilder()
                    .setId(category.id.toString())
                    .setName(category.name)
                    .build()
            )
            .setAuthor(
                NewAutorResponse.newBuilder()
                    .setId(autor.id.toString())
                    .setNome(autor.nome)
                    .setEmail(autor.email)
                    .setDescricao(autor.descricao)
                    .setCriadoEm(autor.criadaEm.toTimestamp())
                    .build()
            )
            .build()
    }

    fun details(): BookDetailResponse {
        return BookDetailResponse.newBuilder()
            .setTitle(title)
            .setAbstract(abstracts)
            .setSummary(summary)
            .setPrice(price.toDouble())
            .setPagesNumber(pagesNumber)
            .setIsbn(isbn)
            .setInicialDates(inicialDates.toTimestamp())
            .setAuthor(
                BookDetailResponse.Author.newBuilder()
                    .setNome(autor.nome)
                    .setEmail(autor.email)
                    .setDescricao(autor.descricao)
                    .build()
            )
            .setCategory(
                BookDetailResponse.Category.newBuilder()
                    .setName(category.name)
                    .build()
            )
            .build()
    }
}