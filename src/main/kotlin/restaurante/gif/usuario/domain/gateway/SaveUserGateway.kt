package restaurante.gif.usuario.domain.gateway

import restaurante.gif.usuario.domain.model.User

interface SaveUserGateway {
    fun execute(user: User): User
}