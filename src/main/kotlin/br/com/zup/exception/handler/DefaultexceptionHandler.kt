package br.com.zup.exception.handler

import br.com.zup.exception.ExceptionHandler
import br.com.zup.exception.StatusWithDatails
import io.grpc.Status
import java.lang.IllegalStateException

class DefaultexceptionHandler : ExceptionHandler<Exception> {
    override fun handle(e: Exception): StatusWithDatails {
        when(e){
            is IllegalArgumentException -> Status.INVALID_ARGUMENT.withDescription(e.message)
            is IllegalStateException -> Status.FAILED_PRECONDITION.withDescription(e.message)
//            is HttpClientResponseException -> Status.INTERNAL.withDescription("Erro interno de conexão!")
            else -> Status.UNKNOWN
        }.let { status->
            return StatusWithDatails(status.withCause(e))
        }
    }

    override fun supports(e: Exception): Boolean {
        return true
    }

}
