package ar.com.unq.eis.trainup.services

import ar.com.unq.eis.trainup.model.Usuario

interface UsuarioService {

    fun crearUsuario(usuario: Usuario): Usuario

    fun obtenerUsuarios(): List<Usuario>

    fun obtenerUsuarioPorId(id: String): Usuario

    fun actualizarUsuario(usuarioActualizado: Usuario): Usuario

    fun eliminarUsuario(id: String)
}
