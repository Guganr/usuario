package restaurant.gif.user.gateway

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.stereotype.Repository

@Repository
//@EnableMongoRepositories( mongoTemplateRef = "userRepository")
internal interface MongoTemplate : MongoRepository<UserEntity, String>