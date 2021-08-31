package restaurante.gif.usuario.domain.usecase

import restaurante.gif.usuario.domain.gateway.ListAllUsersGateway
import restaurante.gif.usuario.domain.model.User
import javax.inject.Named

@Named
class ListAllUsersUseCase(
    private val listAllUsersGateway: ListAllUsersGateway,
) {

    @Throws(NonUserFoundException::class)
    fun execute(): List<User> = listAllUsersGateway.execute() ?: throw NonUserFoundException()

    class NonUserFoundException() : Throwable() {

    }
}