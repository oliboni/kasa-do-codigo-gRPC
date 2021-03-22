package br.com.zup.autor

import br.com.zup.validator.UniqueValue
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank
    val nome: String?,
    @field:NotBlank
    @field:Email
    @field:UniqueValue(domainClassName = "Autor",attribute = "email", message = "Email já cadastrado!")
    val email: String?,
    @field:NotBlank
    @field:Size(max = 400)
    val descricao: String?
) {
    fun toModel(): Autor {
        return Autor(
            nome = nome!!,
            email = email!!,
            descricao = descricao!!
        )
    }
}
