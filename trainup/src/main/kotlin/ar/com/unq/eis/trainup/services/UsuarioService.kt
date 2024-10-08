package ar.com.unq.eis.trainup.services

import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.model.Usuario

interface UsuarioService {

    fun crearUsuario(usuario: Usuario): Usuario

    fun obtenerUsuarios(): List<Usuario>

    fun obtenerUsuarioPorUsername(username: String): Usuario

    fun obtenerUsuarioPorID(id:String): Usuario

    fun actualizarUsuario(usuarioActualizado: Usuario): Usuario

    fun eliminarUsuario(id: String)

    fun logIn(username: String, password: String): Usuario

    fun completarRutina(usuarioID:String, rutinaID:String)
}
