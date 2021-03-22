package br.com.zup.exception

import br.com.zup.exception.handler.DefaultexceptionHandler
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExceptionHandlerResolver(
    @Inject private val handlers: List<ExceptionHandler<Exception>>
) {
    private var defaultHandler: ExceptionHandler<Exception> = DefaultexceptionHandler()

    constructor(handlers: List<ExceptionHandler<Exception>>,defaultHandler: ExceptionHandler<Exception>): this(handlers){
        this.defaultHandler = defaultHandler
    }

    fun resolver(e: Exception): ExceptionHandler<Exception>{
        val foundHandlers = handlers.filter {
            it.supports(e)
        }
        if (foundHandlers.size > 1)
            throw IllegalStateException("Too many handlers supporting the same exception handler")
        return foundHandlers.firstOrNull() ?: defaultHandler
    }
}