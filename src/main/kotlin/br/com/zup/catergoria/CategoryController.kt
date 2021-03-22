package br.com.zup.catergoria

import br.com.zup.NewCategoryResponse
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.validation.Valid

@Validated
@Singleton
class CategoryController(
    private val categoryRepository: CategoryRepository
){

    fun cadastra(@Valid request: NovaCategoriaRequest): NewCategoryResponse? {
        categoryRepository.save(request.toModel()).let{
            return NewCategoryResponse.newBuilder()
                .setId(it.id.toString())
                .setName(it.name)
                .build()
        }
    }
}
