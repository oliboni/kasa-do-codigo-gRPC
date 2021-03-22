package br.com.zup.book

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface BookRepository :JpaRepository<Book,UUID>{

    fun findByAutorId(autorId: UUID): List<Book>
}
