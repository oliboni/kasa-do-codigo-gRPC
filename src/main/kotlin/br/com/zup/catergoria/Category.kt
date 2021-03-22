package br.com.zup.catergoria

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Category (
    val name: String
){
    @Id
    @GeneratedValue
    var id: UUID? = null
}
