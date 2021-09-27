package restaurant.gif.user.model

import restaurant.gif.user.model.User

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