package br.com.zup.autor

import br.com.zup.NewAutorResponse
import java.time.LocalDateTime
import java.time.ZoneId

class NovoAutorResponse(
    autor: Autor
) {
    val id: String = autor.id.toString()
    val nome: String = autor.nome
    val email: String = autor.email
    val descricao: String =autor.descricao
    val criadoEm: LocalDateTime = autor.criadaEm

    fun convert(): NewAutorResponse {
        return NewAutorResponse.newBuilder()
            .setId(id.toString())
            .setNome(nome)
            .setEmail(email)
            .setDescricao(descricao)
            .setCriadoEm(
                criadoEm.atZone(ZoneId.of("UTC")).toInstant().let {
                    com.google.protobuf.Timestamp.newBuilder()
                        .setSeconds(it.epochSecond)
                        .setNanos(it.nano)
                        .build()
                }
            )
            .build()
    }
}
