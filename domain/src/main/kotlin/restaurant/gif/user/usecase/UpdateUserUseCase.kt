package restaurant.gif.user.usecase

import restaurant.gif.user.gateway.UpdateUserGateway
import restaurant.gif.user.model.User
import restaurant.gif.user.exceptions.UserNotFoundException
import javax.inject.Named

@Named
class UpdateUserUseCase(
    private val updateUserGateway: UpdateUserGateway,
    private val listUserByIdUseCase: ListUserByIdUseCase,
) {

    @Throws(UserNotFoundException::class)
    fun execute(id: String, user: User){
        (listUserByIdUseCase.execute(id) ?: UserNotFoundException(id))
            .let {
                updateUserGateway.execute(id, user)
            }
    }

}


