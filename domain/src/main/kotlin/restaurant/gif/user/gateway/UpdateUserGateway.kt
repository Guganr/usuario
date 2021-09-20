package restaurant.gif.user.domain.gateway

import restaurant.gif.user.domain.model.User

interface UpdateUserGateway {
    fun execute(id: String, user: User)
}