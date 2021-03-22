package br.com.zup.book

import br.com.zup.autor.Autor
import br.com.zup.catergoria.Category
import br.com.zup.validator.UniqueValue
import br.com.zup.validator.ValidUUID
import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.validation.constraints.*

@Introspected
data class NovoLivroRequest(
    @field:NotBlank
    @field:UniqueValue(domainClassName = "Book", attribute = "title", message = "Title already exists!")
    val title: String?,

    @field:NotBlank
    @field:Size(max = 500)
    val abstract: String?,

    val summary: String?,

    @field:NotNull
    @field:Min(20)
    val price: BigDecimal?,

    @field:NotNull
    @field:Min(100)
    val pagesNumber: Int?,

    @field:NotBlank
//    @field:UniqueValue
    val isbn: String?,

    @field:Future
    val inicialDates: LocalDateTime?,

    @field:NotBlank
    @field:ValidUUID
    val categoryId: String?,

    @field:NotBlank
    @field:ValidUUID
    val authorId: String?
) {
    fun toModel(
        category: Category,
        autor: Autor
    ): Book {
        return Book(
            title = title!!,
            abstracts = abstract!!,
            summary = summary!!,
            price = price!!,
            pagesNumber = pagesNumber!!,
            isbn = isbn!!,
            inicialDates = inicialDates!!,
            category,
            autor
        )
    }
}
