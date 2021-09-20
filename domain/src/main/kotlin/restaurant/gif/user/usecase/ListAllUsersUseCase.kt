package restaurant.gif.user.domain.usecase

import restaurant.gif.user.domain.gateway.ListAllUsersGateway
import restaurant.gif.user.domain.model.User
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