package restaurante.gif.usuario.domain.usecase

import restaurante.gif.usuario.domain.gateway.UpdateUserGateway
import restaurante.gif.usuario.domain.model.User
import restaurante.gif.usuario.domain.usecase.ListUserByIdUseCase.UserNotFoundException
import javax.inject.Named

@Named
class UpdateUserUseCase(
    private val updateUserGateway: UpdateUserGateway,
    private val listUserByIdUseCase: ListUserByIdUseCase,
) {

    @Throws(UserNotFoundException::class)
    fun execute(id: String, user: User){
        listUserByIdUseCase.execute(id)
            .let {
                updateUserGateway.execute(id, user)
            }
    }

}


