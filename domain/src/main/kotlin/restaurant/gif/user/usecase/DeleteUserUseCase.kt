package restaurant.gif.user.usecase

import restaurant.gif.user.gateway.DeleteUserGateway
import restaurant.gif.user.gateway.ListUserByIdGateway
import restaurant.gif.user.exceptions.UserNotFoundException
import javax.inject.Named

@Named
class DeleteUserUseCase(
    private val deleteUserGateway: DeleteUserGateway,
    private val listUserByIdGateway: ListUserByIdGateway,
) {
    @Throws(UserNotFoundException::class)
    fun executeDelete(id: String) =
        (listUserByIdGateway.execute(id)?: throw UserNotFoundException(id))
            .let {
                deleteUserGateway.executeDelete(id)
            }
}