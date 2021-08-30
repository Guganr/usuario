package restaurante.gif.usuario.domain.model

import java.time.LocalDateTime

data class User(
    val id: String?,
    val nome: String,
    val email: String,
    val senha: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now()
)