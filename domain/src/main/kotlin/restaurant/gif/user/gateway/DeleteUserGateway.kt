package restaurant.gif.user.domain.gateway

interface DeleteUserGateway {
    fun executeDelete(id: String)
}