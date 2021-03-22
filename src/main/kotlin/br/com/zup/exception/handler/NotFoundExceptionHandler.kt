package br.com.zup.exception.handler

import br.com.zup.exception.ExceptionHandler
import br.com.zup.exception.NotFoundException
import br.com.zup.exception.StatusWithDatails
import io.grpc.Status
import javax.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
class NotFoundExceptionHandler:ExceptionHandler<NotFoundException> {
    override fun handle(e: NotFoundException): StatusWithDatails {
        return StatusWithDatails(
            Status.NOT_FOUND
                .withDescription(e.message)
                .withCause(e)
        )
    }

    override fun supports(e: Exception): Boolean {
        return e is NotFoundException
    }
}