package restaurante.gif.usuario.domain.usecase

import restaurante.gif.usuario.domain.gateway.ListUserByIdGateway
import restaurante.gif.usuario.domain.model.User
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