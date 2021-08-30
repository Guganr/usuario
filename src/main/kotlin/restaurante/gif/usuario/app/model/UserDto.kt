package restaurante.gif.usuario.app.model

import restaurante.gif.usuario.domain.model.User

data class UserDto(
    val id: String?,
    val nome: String,
    val email: String,
    val senha: String) {

    companion object {
        fun fromDomain(user : User) =
            with(user) {
                UserDto(
                    id = id,
                    nome = nome,
                    email = email,
                    senha = senha,
                )
            }
    }
}