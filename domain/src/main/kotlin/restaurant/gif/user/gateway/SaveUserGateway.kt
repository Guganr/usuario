package restaurant.gif.user.gateway

import restaurant.gif.user.model.User

interface SaveUserGateway {
    fun execute(user: User): User
}