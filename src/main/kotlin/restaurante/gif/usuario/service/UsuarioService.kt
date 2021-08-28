package restaurante.gif.usuario.service

import org.springframework.stereotype.Service
import restaurante.gif.usuario.dto.UsuarioDto
import restaurante.gif.usuario.model.Usuario
import restaurante.gif.usuario.repository.UsuarioRepository

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
) {


    fun listarTodosUsuarios(): List<UsuarioDto> = usuarioRepository.findAll()
        //Serve para listas. itera em cada elemento
        .map {
            it.toDto().copy(nome = "Gustavo")
        }

    fun cadastrarUsuarioNovo(usuario: Usuario): UsuarioDto =
        usuarioRepository.save(usuario)
            //é para um objeto
            .let(Usuario::toDto)

    fun atualizarUsuario(id: String, usuario: Usuario): UsuarioDto =
    usuarioRepository.findById(id).map(u -> {
        u.
    });

}


