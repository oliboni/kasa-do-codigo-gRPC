package br.com.zup.catergoria

import br.com.zup.NewCategoryRequest

fun NewCategoryRequest.toModel(): NovaCategoriaRequest {
    return NovaCategoriaRequest(
        name = name
    )
}