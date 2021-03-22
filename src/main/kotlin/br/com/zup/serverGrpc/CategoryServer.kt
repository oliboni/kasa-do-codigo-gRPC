package br.com.zup.serverGrpc

import br.com.zup.CategoryServiceGrpc
import br.com.zup.NewCategoryRequest
import br.com.zup.NewCategoryResponse
import br.com.zup.catergoria.CategoryController
import br.com.zup.catergoria.toModel
import br.com.zup.exception.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@ErrorHandler
@Singleton
class CategoryServer(
    private val categoriaController: CategoryController
) : CategoryServiceGrpc.CategoryServiceImplBase() {

    override fun register(request: NewCategoryRequest?, responseObserver: StreamObserver<NewCategoryResponse>?) {
        responseObserver!!.onNext(categoriaController.cadastra(request!!.toModel()))
        responseObserver.onCompleted()
    }
}