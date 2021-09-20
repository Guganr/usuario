package restaurant.gif.user.domain.usecase

import restaurant.gif.user.domain.gateway.ListUserByIdGateway
import restaurant.gif.user.domain.model.User
import javax.inject.Named

@Named
class ListUserByIdUseCase(
    private val listUserByIdGateway: ListUserByIdGateway,
)  {

    @Throws(UserNotFoundException::class)
    fun execute(id: String): User = listUserByIdGateway.execute(id) ?: throw UserNotFoundException(id)

    class UserNotFoundException(id: String) : Throwable() {

    }
}