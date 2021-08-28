package restaurante.gif.usuario.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import restaurante.gif.usuario.dto.UsuarioDto
import java.time.LocalDateTime

@Document(collection = "usuario")
data class Usuario(
    @Id
    val id: ObjectId = ObjectId.get(),
    val nome: String,
    val email: String,
    val senha: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now()
) {

    fun toDto() = UsuarioDto(
        id = id,
        nome = nome,
        email = email,
        senha = senha,
    )
}