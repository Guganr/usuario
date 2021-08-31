package restaurante.gif.usuario.domain.gateway

interface DeleteUserGateway {
    fun executeDelete(id: String)
}