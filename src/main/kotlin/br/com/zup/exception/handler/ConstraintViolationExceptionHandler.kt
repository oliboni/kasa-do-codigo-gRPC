package br.com.zup.exception.handler

import br.com.zup.exception.ExceptionHandler
import br.com.zup.exception.StatusWithDatails
import io.grpc.Status
import javax.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
class ConstraintViolationExceptionHandler:ExceptionHandler<ConstraintViolationException> {
    override fun handle(e: ConstraintViolationException): StatusWithDatails {
        return StatusWithDatails(
            Status.INVALID_ARGUMENT
                .withDescription(e.message)
                .withCause(e)
        )
    }

    override fun supports(e: Exception): Boolean {
        return e is ConstraintViolationException
    }
}