package restaurant.gif.user.app.entrypoint

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import restaurant.gif.user.app.model.UserDto
import restaurant.gif.user.domain.model.User
import restaurant.gif.user.domain.usecase.*
import javax.validation.Valid


@RestController
@RequestMapping("/user")
class UserController(
    private val listAllUsersUseCase: ListAllUsersUseCase,
    private val userByIdUseCase: ListUserByIdUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
) {

    @GetMapping
    fun listAllUsers(
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<List<UserDto>>? =
        runCatching {
            listAllUsersUseCase.execute()
                .map { UserDto.fromDomain(it) }
                .let { it to uriBuilder.path("/usuario/").build().toUri() }
                .let { (user, uri) -> ResponseEntity.created(uri).body(user) }
        }.onFailure {
            when (it) {
                is ListAllUsersUseCase.NonUserFoundException -> ResponseEntity.noContent().build<UserDto>()
                else -> throw it
            }
        }.getOrThrow()

    @GetMapping("/{id}")
    fun listUserById(
        @PathVariable id: String,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<UserDto> =
        runCatching {
            userByIdUseCase.execute(id)
                .let { it to uriBuilder.path("/usuario/${it.id}").build().toUri() }
                .let { (user, uri) -> ResponseEntity.created(uri).body(UserDto.fromDomain(user)) }
        }.onFailure {
            when (it) {
                is ListUserByIdUseCase.UserNotFoundException -> ResponseEntity.notFound().build<UserDto>()
                else -> throw it
            }
        }.getOrThrow()

    @PostMapping
    fun saveUser(
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
            is ListUserByIdUseCase.UserNotFoundException -> ResponseEntity.notFound().build<UserDto>()
            else -> throw it
        }
    }.getOrThrow()

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable(value = "id") id: String,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<UserDto> = runCatching {
        deleteUserUseCase.executeDelete(id)
            .let { ResponseEntity.noContent().build<UserDto>() }
    }.onFailure {
        when (it) {
            is ListAllUsersUseCase.NonUserFoundException -> ResponseEntity.notFound().build<UserDto>()
            else -> throw it
        }
    }.getOrThrow()
}