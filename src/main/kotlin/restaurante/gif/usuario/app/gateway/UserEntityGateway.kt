package restaurante.gif.usuario.app.gateway

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import restaurante.gif.usuario.domain.gateway.*
import restaurante.gif.usuario.domain.model.User
import restaurante.gif.usuario.domain.usecase.ListUserByIdUseCase.UserNotFoundException

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
        (userRepository.findByIdOrNull(id) ?: throw UserNotFoundException(id))
            .copy(id = id, name = user.name, email = user.email, password = user.password)
            .let(userRepository::save)
    }
}