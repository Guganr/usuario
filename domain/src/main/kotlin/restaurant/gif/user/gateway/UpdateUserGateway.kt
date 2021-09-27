package restaurant.gif.user.gateway

import restaurant.gif.user.model.User

interface UpdateUserGateway {
    fun execute(id: String, user: User)
}