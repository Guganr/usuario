package restaurante.gif.usuario.app.entrypoint

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import restaurante.gif.usuario.app.model.UserDto
import restaurante.gif.usuario.domain.model.User
import restaurante.gif.usuario.domain.usecase.*
import restaurante.gif.usuario.domain.usecase.ListAllUsersUseCase.NonUserFoundException
import restaurante.gif.usuario.domain.usecase.ListUserByIdUseCase.UserNotFoundException
import javax.validation.Valid

@RestController
@RequestMapping("/usuario")
class UsuarioController(
    private val userByIdUseCase: ListUserByIdUseCase,
    private val listAllUsersUseCase: ListAllUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) {

    @GetMapping
    fun listarTodosUsuarios(
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<List<UserDto>>? =
        runCatching {
            listAllUsersUseCase.execute()
                .map { UserDto.fromDomain(it) }
                .let { it to uriBuilder.path("/usuario/").build().toUri() }
                .let { (user, uri) -> ResponseEntity.created(uri).body(user) }
        }.onFailure {
            when (it) {
                is NonUserFoundException -> ResponseEntity.noContent().build<UserDto>()
                else -> throw it
            }
        }.getOrThrow()

    @GetMapping("/{id}")
    fun listarUsuarioPorId(
        @PathVariable id: String,
        uriBuilder: UriComponentsBuilder,
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

    @PostMapping
    fun cadastrarUsuarioNovo(
        @RequestBody @Valid user: User,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<UserDto> = saveUserUseCase.execute(user)
        .let {
            it to uriBuilder.path("/usuario/${it.id}").build().toUri()
        }.let { (user, uri) ->
            ResponseEntity.created(uri).body(UserDto.fromDomain(user))
        }

    @PutMapping("/{id}")
    fun updateUser(
        @RequestBody @Valid user: User,
        @PathVariable(value = "id") id: String,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<UserDto> = runCatching {
        updateUserUseCase.execute(id, user)
            .let { ResponseEntity.noContent().build<UserDto>() }
    }.onFailure {
        when (it) {
            is UserNotFoundException -> ResponseEntity.notFound().build<UserDto>()
            else -> throw it
        }
    }.getOrThrow()

    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable(value = "id") id: String,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<UserDto> = runCatching {
        deleteUserUseCase.executeDelete(id)
            .let { ResponseEntity.noContent().build<UserDto>() }
    }.onFailure {
        when (it) {
            is NonUserFoundException -> ResponseEntity.notFound().build<UserDto>()
            else -> throw it
        }
    }.getOrThrow()
}