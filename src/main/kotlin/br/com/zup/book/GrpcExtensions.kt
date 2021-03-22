package br.com.zup.book

import br.com.zup.BookDetailRequest
import br.com.zup.ListBooksRequest
import br.com.zup.NewBookRequest
import br.com.zup.shared.toLocalDateTime
import java.math.BigDecimal

fun NewBookRequest.toModel():NovoLivroRequest{
    return NovoLivroRequest(
        title = title,
        abstract = abstract,
        summary = summary,
        price = BigDecimal(price),
        pagesNumber = pagesNumber,
        isbn = isbn,
        inicialDates = inicialDates.toLocalDateTime(),
        categoryId = categoryId,
        authorId = authorId
    )
}

fun ListBooksRequest.toModel(): ListaLivrosRequest{
    return ListaLivrosRequest(
        authorId = authorId
    )
}

fun BookDetailRequest.toModel(): DetalhesLivroRequest{
    return DetalhesLivroRequest(
        bookId
    )
}