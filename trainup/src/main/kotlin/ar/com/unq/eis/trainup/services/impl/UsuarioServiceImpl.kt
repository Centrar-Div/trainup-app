package ar.com.unq.eis.trainup.services.impl

import ar.com.unq.eis.trainup.controller.Exceptions.RutinaException
import ar.com.unq.eis.trainup.controller.Exceptions.UsuarioException
import ar.com.unq.eis.trainup.dao.RutinaDAO
import ar.com.unq.eis.trainup.dao.UsuarioDAO
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.model.Usuario
import ar.com.unq.eis.trainup.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UsuarioServiceImpl(@Autowired private val usuarioDAO: UsuarioDAO,
                         @Autowired private val rutinaDAO: RutinaDAO) : UsuarioService {

    override fun crearUsuario(usuario: Usuario): Usuario {
        val username = usuario.username
        if (usuarioDAO.findByUsername(username) == null) {
            return usuarioDAO.save(usuario)
        } else {
            throw UsuarioException("Ya existe un usuario con username: ${username}")
        }
    }

    override fun obtenerUsuarios(): List<Usuario> {
        return usuarioDAO.findAll()
    }

    override fun obtenerUsuarioPorUsername(username: String): Usuario {
        return usuarioDAO.findByUsername(username) ?: throw UsuarioException("No existe usuario ${username}")
    }

    override fun obtenerUsuarioPorID(id: String): Usuario {
        return usuarioDAO.findByIdOrNull(id) ?: throw UsuarioException("No existe usuario con id ${id}")
    }

    override fun actualizarUsuario(usuarioActualizado: Usuario): Usuario {
        if (usuarioDAO.existsById(usuarioActualizado.id!!)) {
            return usuarioDAO.save(usuarioActualizado)
        } else {
            throw UsuarioException("No existe usuario con id ${usuarioActualizado.id!!}")
        }

    }

    override fun eliminarUsuario(id: String) {
        if (usuarioDAO.existsById(id)) {
            usuarioDAO.deleteById(id)
        } else {
            throw UsuarioException("No existe usuario con id ${id}")
        }

    }

    override fun logIn(username: String, password: String): Usuario {
        return usuarioDAO.findByUsernameAndPassword(username, password)
            .orElseThrow { UsuarioException("usuario no encontrado") }
    }

    override fun completarRutina(usuarioID:String, rutinaID: String) {
        val usuario = this.obtenerUsuarioPorID(usuarioID)
        val rutina = getRutinaByID(rutinaID)

        try {
            usuario.completarRutina(rutina)
            actualizarUsuario(usuario)
        } catch (e: UsuarioException) {
            throw UsuarioException(e.message!!)
        }

    }

    private fun getRutinaByID(rutinaID: String): Rutina {
        val rutina =
            this.rutinaDAO.findByIdOrNull(rutinaID) ?: throw RutinaException("No existe rutina con id ${rutinaID}")
        return rutina
    }

    override fun updateFollowRutina(usuarioID: String, rutinaID: String):Usuario {
        val usuario = this.obtenerUsuarioPorID(usuarioID)
        val rutina = this.getRutinaByID(rutinaID)

        usuario.followUnfollowRutina(rutina)

        return usuarioDAO.save(usuario)
    }

    override fun isFollowing(usuarioID: String, rutinaID: String): Boolean {
        val usuario = this.obtenerUsuarioPorID(usuarioID)
        val rutina = this.getRutinaByID(rutinaID)

        return usuario.isFollowing(rutina)

    }

    override fun completarEjercicio(userId: String, rutinaId: String, ejercicioId: String) {
        val usuario = this.obtenerUsuarioPorID(userId)
        val rutina = this.getRutinaByID(rutinaId)
        val ejercicio = rutina.ejercicios.find { it.id == ejercicioId }
            ?: throw RutinaException("No existe ejercicio con id ${ejercicioId} en la rutina ${rutinaId}")

        rutina.ejercicios.map { if (it.id == ejercicioId){
            it.completado = true
        } }

        rutinaDAO.save(rutina)
        usuario.completarEjercicio(rutinaId, ejercicioId)

        this.actualizarUsuario(usuario)
    }

    override fun agregarRutinaFavorita(idUsuario: String, idRutina: String): Usuario {
        val usuario = this.obtenerUsuarioPorID(idUsuario)
        val rutina = this.getRutinaByID(idRutina)

        usuario.agregarRutinaFavorita(rutina)

        return usuarioDAO.save(usuario)
    }
}