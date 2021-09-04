package restaurant.gif.user.app.gateway

import org.springframework.data.mongodb.repository.MongoRepository

internal interface UserRepository : MongoRepository<UserEntity, String>