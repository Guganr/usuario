package restaurant.gif.user.domain.gateway

import restaurant.gif.user.domain.model.User


interface ListUserByIdGateway  {
    fun execute(id: String): User?
}