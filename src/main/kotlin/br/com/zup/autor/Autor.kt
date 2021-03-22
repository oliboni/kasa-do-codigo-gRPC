package br.com.zup.autor

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
class Autor(
    @field:NotBlank
    @Column(nullable = false)
    val nome: String,

    @field:NotBlank
    @field:Email
    @Column(nullable = false, unique = true)
    val email: String,

    @field:NotBlank
    @field:Size(max=400)
    @Column(nullable = false)
    val descricao: String,
){
    @Id
    @GeneratedValue
    var id: UUID? = null

    @Column(nullable = false, updatable = false)
    val criadaEm: LocalDateTime = LocalDateTime.now()
}