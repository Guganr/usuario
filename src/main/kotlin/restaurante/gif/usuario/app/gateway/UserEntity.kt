package restaurante.gif.usuario.app.gateway

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import restaurante.gif.usuario.domain.model.User
import java.time.LocalDateTime


@Document(collection = "usuario")
internal data class UserEntity(
    @Id
    val id: String?,
    val nome: String,
    val email: String,
    val senha: String,
    val dataCriacao: LocalDateTime
) {
    fun toDomain() =
        User(
            id = id,
            nome = nome,
            email = email,
            senha = senha,
            dataCriacao = dataCriacao
        )
}