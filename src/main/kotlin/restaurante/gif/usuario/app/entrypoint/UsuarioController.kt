package restaurante.gif.usuario.app.entrypoint

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import restaurante.gif.usuario.app.model.UserDto
import restaurante.gif.usuario.app.model.UserDto.Companion.fromDomain
import restaurante.gif.usuario.domain.model.User
import restaurante.gif.usuario.domain.usecase.ListAllUsersUseCase
import restaurante.gif.usuario.domain.usecase.ListUserByIdUseCase
import restaurante.gif.usuario.domain.usecase.ListUserByIdUseCase.UserNotFoundException

@RestController
@RequestMapping("/usuario")
class UsuarioController(
    private val userByIdUseCase: ListUserByIdUseCase,
    private val listAllUsersUseCase: ListAllUsersUseCase,
) {

    @GetMapping
    fun listarTodosUsuarios(
    uriBuilder: UriComponentsBuilder
    ): ResponseEntity<List<UserDto>>? =
        runCatching {
        listAllUsersUseCase.execute()
            .map { UserDto.fromDomain(it) }
            .let { it to uriBuilder.path("/usuario/").build().toUri() }
            .let { (user, uri) -> ResponseEntity.created(uri).body(user) }
        }.onFailure {
            when (it) {
                is ListAllUsersUseCase.NonUserFoundException -> ResponseEntity.notFound().build<UserDto>()
                else -> throw it
            }
        }.getOrThrow()

//    runCatching {
//            .let { it to uriBuilder.path("/usuario/${it.id}").build().toUri() }
//            .let {
//                    (user, uri) -> ResponseEntity.created(uri).body(UsuarioDto.fromDomain(user)) }
//    }.onFailure {
//        when (it) {
//            is UserNotFoundException -> ResponseEntity.notFound().build<UsuarioDto>()
//            else -> throw it
//        }
//    }.getOrThrow()
//
//    @PostMapping
//    fun cadastrarUsuarioNovo(
//        @RequestBody @Valid user: User,
//        uriBuilder: UriComponentsBuilder
//    ): ResponseEntity<UsuarioDto> = service.cadastrarUsuarioNovo(user)
//        .let {
//            it to uriBuilder.path("/usuario/${it.id}").build().toUri()
//        }.let { (usuario, uri) ->
//            ResponseEntity.created(uri).body(usuario)
//        }
//
//    @PutMapping("/{id}")
//    fun atualizarUsuario(
//        @RequestBody @Valid user: User,
//        @PathVariable id: String,
//        uriBuilder: UriComponentsBuilder
//    ): ResponseEntity<UsuarioDto> = service.atualizarUsuario(id, user)
//        .let {
//            it to uriBuilder.path("/usuario/${it.id}").build().toUri()
//        }.let { (usuario, uri) ->
//            ResponseEntity.created(uri).body(usuario)
    @GetMapping("/{id}")
    fun listarUsuarioPorId(
        @PathVariable id: String,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UserDto> =
        runCatching {
            userByIdUseCase.execute(id)
                .let { it to uriBuilder.path("/usuario/${it.id}").build().toUri() }
                .let { (user, uri) -> ResponseEntity.created(uri).body(UserDto.fromDomain(user)) }
        }.onFailure {
            when (it) {
                is UserNotFoundException -> ResponseEntity.notFound().build<UserDto>()
                else -> throw it
            }
        }.getOrThrow()


//        try {
//            userByIdUseCase.execute(id)
//                .let { it to uriBuilder.path("/usuario/${it.id}").build().toUri() }
//                .let { (user, uri) -> ResponseEntity.created(uri).body(UsuarioDto.fromDomain(user)) }
//        } catch (userNotFoundException: UserNotFoundException) {
//            ResponseEntity.notFound().build()
//        }
//
//    @DeleteMapping("/{id}")
//    fun deleteById(@PathVariable(value = "id") id: String) =
//        service.deletarUsuario(id).apply {
//            ResponseEntity<HttpStatus>(HttpStatus.OK)
//        }
}