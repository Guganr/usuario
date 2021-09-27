package restaurant.gif.user.gateway

import restaurant.gif.user.model.User


interface ListUserByIdGateway  {
    fun execute(id: String): User?
}