package restaurante.gif.usuario.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import restaurante.gif.usuario.dto.UsuarioDto
import restaurante.gif.usuario.model.Usuario
import restaurante.gif.usuario.service.UsuarioService
import javax.validation.Valid

@RestController
@RequestMapping("/usuario")
class UsuarioController(private val service: UsuarioService) {

    @GetMapping
    fun listarTodosUsuarios() =
        service.listarTodosUsuarios()
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun cadastrarUsuarioNovo(
        @RequestBody @Valid usuario: Usuario,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UsuarioDto> = service.cadastrarUsuarioNovo(usuario)
        .let {
            it to uriBuilder.path("/usuario/${it.id}").build().toUri()
        }.let { (usuario, uri) ->
            ResponseEntity.created(uri).body(usuario)
        }

    @PutMapping("/{id}")
    fun cadastrar(
        @RequestBody @Valid usuario: Usuario,
        @PathVariable id: String,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UsuarioDto> = service.atualizarUsuarioNovo(id, usuario)
        .let {
            it to uriBuilder.path("/usuario/${it.id}").build().toUri()
        }.let { (usuario, uri) ->
            ResponseEntity.created(uri).body(usuario)
        }
}