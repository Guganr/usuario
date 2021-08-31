package restaurante.gif.usuario.domain.gateway

import restaurante.gif.usuario.domain.model.User

interface UpdateUserGateway {
    fun execute(id: String, user: User)
}