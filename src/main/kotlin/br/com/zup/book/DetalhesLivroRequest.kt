package br.com.zup.book

import br.com.zup.validator.ValidUUID
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class DetalhesLivroRequest (
    @field:ValidUUID
    @field:NotBlank
    val bookId: String?
)
