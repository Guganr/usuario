package restaurante.gif.usuario.app.model

import restaurante.gif.usuario.domain.model.User

data class UserDto(
    val id: String?,
    val name: String,
    val email: String,
    val password: String) {

    companion object {
        fun fromDomain(user : User) =
            with(user) {
                UserDto(
                    id = id,
                    name = name,
                    email = email,
                    password = password,
                )
            }
    }
}