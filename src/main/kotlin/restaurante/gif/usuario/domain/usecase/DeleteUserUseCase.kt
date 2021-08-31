package restaurante.gif.usuario.domain.usecase

import restaurante.gif.usuario.domain.gateway.DeleteUserGateway
import restaurante.gif.usuario.domain.gateway.ListUserByIdGateway
import restaurante.gif.usuario.domain.usecase.ListUserByIdUseCase.UserNotFoundException

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