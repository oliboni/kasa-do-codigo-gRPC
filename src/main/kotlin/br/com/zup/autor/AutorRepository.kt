package br.com.zup.autor

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository:JpaRepository<Autor,UUID>{

}
