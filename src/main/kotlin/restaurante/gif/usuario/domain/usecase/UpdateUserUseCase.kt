package restaurante.gif.usuario.domain.usecase

import org.springframework.stereotype.Service

@Service
class UpdateUserUseCase(
//    private val usuarioRepository: UsuarioRepository,
) {


    fun atualizarUsuario(id: String, user: restaurante.gif.usuario.domain.model.User): restaurante.gif.usuario.app.model.UserDto =
        TODO()
//        (usuarioRepository.findByIdOrNull(id)
//        ?: throw UserPrincipalNotFoundException(id))
//        .copy(
//            id = id,
//            nome = user.nome,
//            email = user.email,
//            senha = user.senha,
//        )
//        .let(usuarioRepository::save).toDto()
}


