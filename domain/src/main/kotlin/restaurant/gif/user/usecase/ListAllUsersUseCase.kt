package restaurant.gif.user.usecase

import restaurant.gif.user.gateway.ListAllUsersGateway
import restaurant.gif.user.model.User
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