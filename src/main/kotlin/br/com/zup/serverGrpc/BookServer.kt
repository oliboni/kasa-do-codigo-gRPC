package br.com.zup.serverGrpc

import br.com.zup.*
import br.com.zup.book.BookController
import br.com.zup.book.toModel
import br.com.zup.exception.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@ErrorHandler
@Singleton
class BookServer(private val bookController: BookController) : BookServiceGrpc.BookServiceImplBase() {
    override fun register(request: NewBookRequest?, responseObserver: StreamObserver<NewBookResponse>?) {
        responseObserver!!.onNext(bookController.register(request!!.toModel()))
        responseObserver.onCompleted()
    }

    override fun list(request: ListBooksRequest?, responseObserver: StreamObserver<ListBooksResponse>?) {
        responseObserver!!.onNext(bookController.list(request!!.toModel()))
        responseObserver.onCompleted()
    }

    override fun detail(request: BookDetailRequest?, responseObserver: StreamObserver<BookDetailResponse>?) {
        responseObserver!!.onNext(bookController.detail(request!!.toModel()))
        responseObserver.onCompleted()
    }
}