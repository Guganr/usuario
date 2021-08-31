package restaurante.gif.usuario.app.gateway

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import restaurante.gif.usuario.domain.model.User
import java.time.LocalDateTime


@Document(collection = "users")
internal data class UserEntity(
    @Id
    val id: String?,
    val name: String,
    val email: String,
    val password: String,
    val createdDate: LocalDateTime
) {
    fun toDomain() =
        User(
            id = id,
            name = name,
            email = email,
            password = password,
            createdDate = createdDate
        )

    companion object {
        fun fromDomain(user : User) =
            with(user) {
                UserEntity(
                    id = id,
                    name = name,
                    email = email,
                    password = password,
                    createdDate = LocalDateTime.now()
                )
            }
    }
}