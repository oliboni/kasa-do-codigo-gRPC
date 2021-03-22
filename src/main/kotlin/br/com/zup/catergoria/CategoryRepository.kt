package br.com.zup.catergoria

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface CategoryRepository : JpaRepository<Category,UUID>{

}
