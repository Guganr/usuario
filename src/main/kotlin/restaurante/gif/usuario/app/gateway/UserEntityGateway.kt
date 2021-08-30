package restaurante.gif.usuario.app.gateway

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import restaurante.gif.usuario.domain.gateway.ListUserByIdGateway
import restaurante.gif.usuario.domain.gateway.ListAllUsersGateway
import restaurante.gif.usuario.domain.model.User

@Component
internal class UserEntityGateway(
    private val usuarioRepository: UsuarioRepository
) : ListUserByIdGateway, ListAllUsersGateway  {
    override fun execute(id: String): User? =
        usuarioRepository.findByIdOrNull(id)?.toDomain()

    override fun execute(): List<User>? =
        usuarioRepository.findAll().map(UserEntity::toDomain)

}