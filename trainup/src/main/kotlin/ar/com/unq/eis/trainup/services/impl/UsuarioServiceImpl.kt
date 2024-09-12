package ar.com.unq.eis.trainup.services.impl

import ar.com.unq.eis.trainup.dao.UsuarioDAO
import ar.com.unq.eis.trainup.model.Usuario
import ar.com.unq.eis.trainup.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class UsuarioServiceImpl(@Autowired private val usuarioDAO: UsuarioDAO) : UsuarioService {
    override fun crearUsuario(usuario: Usuario): Usuario {
        return usuarioDAO.save(usuario)
    }

    override fun obtenerUsuarios(): List<Usuario> {
        return usuarioDAO.findAll()
    }

    override fun obtenerUsuarioPorId(id: String): Usuario {
        return usuarioDAO.findByIdOrNull(id) ?: throw NoSuchElementException("No existe usuario con id ${id}")
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


    fun logIn(username: String, password: String): Usuario {
        return usuarioDAO.findByUsernameAndPassword(username, password)
            .orElseThrow { NoSuchElementException("Los campos ingresados son incorrectos") }
    }
}