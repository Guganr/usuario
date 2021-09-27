package restaurant.gif.user.gateway

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import restaurant.gif.user.gateway.*
import restaurant.gif.user.model.User

@Component
internal class UserEntityGateway(
    private val userRepository: UserRepository,
) : ListUserByIdGateway, ListAllUsersGateway, DeleteUserGateway, SaveUserGateway, UpdateUserGateway {
    override fun execute(id: String): User? =
        userRepository.findByIdOrNull(id)?.toDomain()

    override fun execute(): List<User>? =
        userRepository.findAll().map(UserEntity::toDomain)

    override fun execute(user: User): User =
        userRepository.save(UserEntity.fromDomain(user)).toDomain()

    override fun executeDelete(id: String) {
        userRepository.deleteById(id)
    }

    override fun execute(id: String, user: User) {
        UserEntity.fromDomain(user)
            .copy(id = id, name = user.name, email = user.email, password = user.password)
            .let(userRepository::save)
    }
}