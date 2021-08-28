package restaurante.gif.usuario.dto

import org.bson.types.ObjectId

data class UsuarioDto(
    val id: ObjectId = ObjectId.get(),
    val nome: String,
    val email: String,
    val senha: String)