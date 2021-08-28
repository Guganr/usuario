package restaurante.gif.usuario.repository

import org.springframework.data.mongodb.repository.MongoRepository
import restaurante.gif.usuario.model.Usuario

interface UsuarioRepository : MongoRepository<Usuario, String>