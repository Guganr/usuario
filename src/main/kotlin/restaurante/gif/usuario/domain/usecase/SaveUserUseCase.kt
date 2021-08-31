package restaurante.gif.usuario.domain.usecase

import restaurante.gif.usuario.domain.gateway.SaveUserGateway
import restaurante.gif.usuario.domain.model.User
import javax.inject.Named

@Named
class SaveUserUseCase(
    private val saveUserGateway: SaveUserGateway,
)  {
    fun execute(user: User): User =
        saveUserGateway.execute(user)
}