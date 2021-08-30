package restaurante.gif.usuario.domain.gateway

import restaurante.gif.usuario.domain.model.User


interface ListUserByIdGateway  {
    fun execute(id: String): User?
}