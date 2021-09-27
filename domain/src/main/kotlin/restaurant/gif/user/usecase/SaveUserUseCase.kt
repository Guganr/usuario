package restaurant.gif.user.usecase

import restaurant.gif.user.gateway.SaveUserGateway
import restaurant.gif.user.model.User
import javax.inject.Named

@Named
class SaveUserUseCase(
    private val saveUserGateway: SaveUserGateway,
)  {
    fun execute(user: User): User =
        saveUserGateway.execute(user)
}