package br.com.zup.book

import br.com.zup.validator.ValidUUID
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class ListaLivrosRequest (
    @field:ValidUUID
    @field:NotBlank
    val authorId: String?
)
