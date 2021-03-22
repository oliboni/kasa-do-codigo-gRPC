package br.com.zup.book

import br.com.zup.BookDetailResponse
import br.com.zup.ListBooksResponse
import br.com.zup.NewBookResponse
import br.com.zup.autor.AutorRepository
import br.com.zup.catergoria.CategoryRepository
import br.com.zup.exception.NotFoundException
import io.micronaut.validation.Validated
import java.util.*
import javax.inject.Singleton
import javax.validation.Valid

@Validated
@Singleton
class BookController(
    private val categoryRepository: CategoryRepository,
    private val autorRepository: AutorRepository,
    private val bookRepository: BookRepository
) {

    fun register(@Valid request: NovoLivroRequest): NewBookResponse? {
        val author = autorRepository.findById(UUID.fromString(request.authorId)).let {
            if (it.isEmpty) {
                throw IllegalArgumentException("Author not found!")
            }
            it.get()
        }
        val category = categoryRepository.findById(UUID.fromString(request.categoryId)).let {
            if (it.isEmpty) {
                throw IllegalArgumentException("Category not found")
            }
            it.get()
        }

        val book = request.toModel(category, author)

        bookRepository.save(book)

        return book.converts()
    }

    fun list(@Valid request: ListaLivrosRequest): ListBooksResponse {
        return bookRepository.findByAutorId(UUID.fromString(request.authorId)).map { book ->
            ListBooksResponse.Books.newBuilder()
                .setBookId(book.id.toString())
                .setTitle(book.title)
                .build()
        }.let {
            ListBooksResponse.newBuilder()
                .addAllBooks(it)
                .build()
        }
    }

    fun detail(@Valid request: DetalhesLivroRequest): BookDetailResponse {
        return bookRepository.findById(UUID.fromString(request.bookId)).let {
            if (it.isEmpty) {
                throw NotFoundException("Book not found")
            }
            it.get()
        }.details()
    }
}