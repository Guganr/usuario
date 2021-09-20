package restaurant.gif.user.domain.model

import java.time.LocalDateTime

data class User(
    val id: String?,
    val name: String,
    val email: String,
    val password: String,
    val createdDate: LocalDateTime = LocalDateTime.now()
)