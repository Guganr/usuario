package restaurant.gif.user.usecase

import restaurant.gif.user.gateway.ListUserByIdGateway
import restaurant.gif.user.model.User
import restaurant.gif.user.exceptions.UserNotFoundException
import javax.inject.Named

@Named
class ListUserByIdUseCase(
    private val listUserByIdGateway: ListUserByIdGateway,
)  {

    @Throws(UserNotFoundException::class)
    fun execute(id: String): User = listUserByIdGateway.execute(id) ?: throw UserNotFoundException(id)

    }


