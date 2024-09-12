package ar.com.unq.eis.trainup.services.impl

import ar.com.unq.eis.trainup.dao.UsuarioDAO
import ar.com.unq.eis.trainup.model.Usuario
import ar.com.unq.eis.trainup.services.UsuarioService
import org.apache.coyote.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UsuarioServiceImpl(@Autowired private val usuarioDAO: UsuarioDAO) : UsuarioService {

    override fun crearUsuario(usuario: Usuario): Usuario {
        return usuarioDAO.save(usuario)
    }

    override fun obtenerUsuarios(): List<Usuario> {
        return usuarioDAO.findAll()
    }

    override fun obtenerUsuarioPorUsername(username: String): Usuario {
        return usuarioDAO.findByUsername(username) ?: throw NoSuchElementException("No existe usuario ${username}")
    }

    override fun actualizarUsuario(usuarioActualizado: Usuario): Usuario {
        var usuario = usuarioDAO.findByIdOrNull(usuarioActualizado.id)
            ?: throw NoSuchElementException("No existe usuario con id ${usuarioActualizado.id}")
        return usuarioDAO.save(usuarioActualizado)
    }

    override fun eliminarUsuario(id: String) {
        if (usuarioDAO.existsById(id)) {
            usuarioDAO.deleteById(id)
        }else{
            throw NoSuchElementException("No existe usuario con id ${id}")
        }

    }

    override fun logIn(username: String, password: String): Usuario {
        return usuarioDAO.findByUsernameAndPassword(username, password)
            .orElseThrow { BadRequestException("Los campos ingresados son incorrectos") }
    }
}