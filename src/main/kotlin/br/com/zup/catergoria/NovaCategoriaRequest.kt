package br.com.zup.catergoria

import br.com.zup.validator.UniqueValue
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class NovaCategoriaRequest (
    @field:NotBlank
    @field:UniqueValue(domainClassName = "Category", attribute = "name", message = "This category name already exists")
    val name: String
) {
    fun toModel(): Category {
        return Category(name)
    }
}
