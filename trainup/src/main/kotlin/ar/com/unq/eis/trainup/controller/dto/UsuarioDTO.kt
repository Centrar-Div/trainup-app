package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Usuario
import java.time.LocalDate


class UsuarioDTO(
    var id: String? = null,
    var username: String = "",
    var password: String = "",
    var rutinasSeguidas: List<RutinaDTO> = mutableListOf(),
    var rutinasCompletadas: List<RutinaDTO> = mutableListOf(),
    var nombre: String = "",
    var edad: Int? = null,
    var fecNacimiento: LocalDate? = null,
    var telefono: String = "",
    var genero: String = "",
    var altura: String = "",
    var peso: String = "",
    var objetivo: String = "",
    var esAdmin: Boolean = false,
    var rutinasFavoritas: List<RutinaDTO> = mutableListOf()
) {

    fun aModelo(): Usuario {
        val usuario = Usuario(username, password, nombre, edad!!, fecNacimiento!!, telefono, genero, altura, peso, objetivo, esAdmin) // Incluir esAdmin
        usuario.id = id
        usuario.rutinasSeguidas.addAll(rutinasSeguidas.map { it.aModelo() })
        usuario.rutinasCompletadas.addAll(rutinasCompletadas.map { it.aModelo() })
        usuario.rutinasFavoritas.addAll(rutinasFavoritas.map { it.aModelo() })

        return usuario
    }

    companion object {
        fun desdeModelo(usuario: Usuario): UsuarioDTO {
            val usuarioDto = UsuarioDTO(
                usuario.id,
                usuario.username,
                usuario.password,
                usuario.rutinasSeguidas.map { RutinaDTO.desdeModelo(it) },
                usuario.rutinasCompletadas.map { RutinaDTO.desdeModelo(it) },
                usuario.nombre,
                usuario.edad,
                usuario.fecNacimiento,
                usuario.telefono,
                usuario.genero,
                usuario.altura,
                usuario.peso,
                usuario.objetivo,
                usuario.esAdmin // Mapear esAdmin
            )

            usuarioDto.rutinasFavoritas = usuario.rutinasFavoritas.map { RutinaDTO.desdeModelo(it) }
            return usuarioDto
        }
    }
}


class UserBodyDTO(
    var id: String? = null,
    var username: String = "",
    var password: String = "",
    var nombre: String = "",
    var edad: Int? = null,
    var fecNacimiento: LocalDate? = null,
    var telefono: String = "",
    var genero: String = "",
    var altura: String = "",
    var peso: String = "",
    var objetivo: String = ""
) {

    fun aModelo(): Usuario {
        return Usuario(
            username = this.username,
            password = this.password,
            nombre = this.nombre,
            edad = this.edad!!,
            fecNacimiento = this.fecNacimiento ?: LocalDate.now(),
            telefono = this.telefono,
            genero = this.genero,
            altura = this.altura,
            peso = this.peso,
            objetivo = this.objetivo
        )
    }

    companion object {
        fun desdeModelo(usuario: Usuario): UserBodyDTO {
            return UserBodyDTO(
                id = usuario.id,
                username = usuario.username,
                password = usuario.password,
                nombre = usuario.nombre,
                fecNacimiento = usuario.fecNacimiento,
                telefono = usuario.telefono,
                genero = usuario.genero,
                altura = usuario.altura,
                edad = usuario.edad,
                peso = usuario.peso,
                objetivo = usuario.objetivo
            )
        }
    }
}

