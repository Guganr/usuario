package restaurant.gif.user.domain.gateway

import restaurant.gif.user.domain.model.User

interface ListAllUsersGateway {
    fun execute(): List<User>?
}