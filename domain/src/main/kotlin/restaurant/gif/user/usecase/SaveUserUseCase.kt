package restaurant.gif.user.domain.usecase

import restaurant.gif.user.domain.gateway.SaveUserGateway
import restaurant.gif.user.domain.model.User
import javax.inject.Named

@Named
class SaveUserUseCase(
    private val saveUserGateway: SaveUserGateway,
)  {
    fun execute(user: User): User =
        saveUserGateway.execute(user)
}