package restaurante.gif.usuario.domain.gateway

import restaurante.gif.usuario.domain.model.User

interface ListAllUsersGateway {
    fun execute(): List<User>?
}