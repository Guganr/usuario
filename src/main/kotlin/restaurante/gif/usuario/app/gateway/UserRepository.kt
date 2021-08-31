package restaurante.gif.usuario.app.gateway

import org.springframework.data.mongodb.repository.MongoRepository

internal interface UserRepository : MongoRepository<UserEntity, String>