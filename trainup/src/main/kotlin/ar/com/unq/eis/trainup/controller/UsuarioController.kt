package ar.com.unq.eis.trainup.controller

import ar.com.unq.eis.trainup.controller.dto.UsuarioDTO
import ar.com.unq.eis.trainup.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/usuario")
class UsuarioController(@Autowired private val usuarioService: UsuarioService) {


    // chequear excepciones (que sean mas claras)
    @PostMapping
    fun crearUsuario(@RequestBody usuarioDTO: UsuarioDTO): ResponseEntity<UsuarioDTO> {
        return try {
            val usuario = usuarioService.crearUsuario(usuarioDTO.aModelo())
            ResponseEntity.ok(UsuarioDTO.desdeModelo(usuario))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }


    @GetMapping("/{id}")
    fun obtenerUsuarioPorID(@PathVariable userId: String): ResponseEntity<UsuarioDTO> {
        return try {
            val usuario = usuarioService.obtenerUsuarioPorId(userId)
            ResponseEntity.ok(UsuarioDTO.desdeModelo(usuario))
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @GetMapping
    fun obtenerUsuarios(): ResponseEntity<List<UsuarioDTO>> {
        return try {
            val usuarios = usuarioService.obtenerUsuarios()
            if (usuarios.isEmpty()) {
                ResponseEntity.noContent().build()
            } else {
                ResponseEntity.ok(usuarios.map { usuario -> UsuarioDTO.desdeModelo(usuario) })
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @PutMapping("/{id}")
    fun actualizarUsuario(@RequestBody usuarioDTO: UsuarioDTO): ResponseEntity<UsuarioDTO> {
        return try {
            val usuario = usuarioService.actualizarUsuario(usuarioDTO.aModelo())
            ResponseEntity.ok(UsuarioDTO.desdeModelo(usuario))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable userId: String): ResponseEntity<Unit> {
        return try {
            usuarioService.eliminarUsuario(userId)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch(e: NoSuchElementException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}