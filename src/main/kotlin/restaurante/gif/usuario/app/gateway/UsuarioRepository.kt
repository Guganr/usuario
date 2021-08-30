package restaurante.gif.usuario.app.gateway

import org.springframework.data.mongodb.repository.MongoRepository

internal interface UsuarioRepository : MongoRepository<UserEntity, String>