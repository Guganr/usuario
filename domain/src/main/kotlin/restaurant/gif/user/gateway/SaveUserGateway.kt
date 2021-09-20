package restaurant.gif.user.domain.gateway

import restaurant.gif.user.domain.model.User

interface SaveUserGateway {
    fun execute(user: User): User
}