package br.com.zup.serverGrpc

import br.com.zup.AutorServiceGrpc
import br.com.zup.NewAutorRequest
import br.com.zup.NewAutorResponse
import br.com.zup.autor.AutorController
import br.com.zup.autor.toModel
import br.com.zup.exception.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@ErrorHandler
@Singleton
class AuthorServer(
    private val autorController: AutorController
) : AutorServiceGrpc.AutorServiceImplBase() {

    override fun cadastro(request: NewAutorRequest?, responseObserver: StreamObserver<NewAutorResponse>?) {
        responseObserver!!.onNext(
            request!!.toModel().let {
                autorController.cadastro(it)
            }.convert()
        )
        responseObserver.onCompleted()
    }
}