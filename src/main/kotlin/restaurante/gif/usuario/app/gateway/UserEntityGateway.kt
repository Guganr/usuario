package restaurante.gif.usuario.app.gateway

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import restaurante.gif.usuario.domain.gateway.*
import restaurante.gif.usuario.domain.model.User
import restaurante.gif.usuario.domain.usecase.ListUserByIdUseCase.UserNotFoundException

@Component
internal class UserEntityGateway(
    private val usuarioRepository: UsuarioRepository,
) : ListUserByIdGateway, ListAllUsersGateway, DeleteUserGateway, SaveUserGateway, UpdateUserGateway {
    override fun execute(id: String): User? =
        usuarioRepository.findByIdOrNull(id)?.toDomain()

    override fun execute(): List<User>? =
        usuarioRepository.findAll().map(UserEntity::toDomain)

    override fun execute(user: User): User =
        usuarioRepository.save(UserEntity.fromDomain(user)).toDomain()

    override fun executeDelete(id: String) {
        usuarioRepository.deleteById(id)
    }

    override fun execute(id: String, user: User) {
        (usuarioRepository.findByIdOrNull(id) ?: throw UserNotFoundException(id))
            .copy(id = id, nome = user.nome, email = user.email, senha = user.senha)
            .let(usuarioRepository::save)
    }
}