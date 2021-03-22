package br.com.zup.autor

import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.validation.Valid

@Validated
@Singleton
class AutorController(private val autorRepository: AutorRepository) {

    fun cadastro(@Valid request: NovoAutorRequest):NovoAutorResponse{
        val autor = request.toModel().let {
            autorRepository.save(it)
        }

        return NovoAutorResponse(autor)
    }

}
