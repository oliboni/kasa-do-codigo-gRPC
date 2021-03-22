package br.com.zup.autor

import br.com.zup.NewAutorRequest

fun NewAutorRequest.toModel():NovoAutorRequest{
    return NovoAutorRequest(
        nome = nome,
        email = email,
        descricao = descricao
    )
}