package restaurant.gif.user.gateway

import restaurant.gif.user.model.User

interface ListAllUsersGateway {
    fun execute(): List<User>?
}