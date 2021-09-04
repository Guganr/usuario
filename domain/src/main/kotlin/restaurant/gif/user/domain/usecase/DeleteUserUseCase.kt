package restaurant.gif.user.domain.usecase

import restaurant.gif.user.domain.gateway.DeleteUserGateway
import restaurant.gif.user.domain.gateway.ListUserByIdGateway
import restaurant.gif.user.domain.usecase.ListUserByIdUseCase.UserNotFoundException

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