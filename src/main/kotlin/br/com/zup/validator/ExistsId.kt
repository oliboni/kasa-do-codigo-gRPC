package br.com.zup.validator

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import java.util.*
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass


@Target(FIELD)
@Retention(RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [ExistsIdValidator::class])
annotation class ExistsId(
    val message: String = "Value already exists",
    val domainClassName: String,
    val attribute: String,
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)

@Singleton
open class ExistsIdValidator(@PersistenceContext private val em: EntityManager):ConstraintValidator<ExistsId,String> {

    @Transactional
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<ExistsId>,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value == null) {
            return true
        }

        val domainClass = annotationMetadata.stringValue("domainClassName").get()
        val attribute = annotationMetadata.stringValue("attribute").get()

        val query = em.createQuery("SELECT 1 FROM $domainClass WHERE $attribute = :value")
            .setParameter("value", UUID.fromString(value))

        return query.resultList.isNotEmpty()
    }

}
