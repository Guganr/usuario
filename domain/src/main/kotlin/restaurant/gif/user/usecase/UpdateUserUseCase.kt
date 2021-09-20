package restaurant.gif.user.domain.usecase

import restaurant.gif.user.domain.gateway.UpdateUserGateway
import restaurant.gif.user.domain.model.User
import restaurant.gif.user.domain.usecase.ListUserByIdUseCase.UserNotFoundException
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


