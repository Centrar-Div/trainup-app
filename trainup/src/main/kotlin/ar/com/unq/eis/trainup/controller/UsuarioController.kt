package ar.com.unq.eis.trainup.controller

import ar.com.unq.eis.trainup.controller.Exceptions.RutinaException
import ar.com.unq.eis.trainup.controller.Exceptions.UsuarioException
import ar.com.unq.eis.trainup.controller.dto.ErrorDTO
import ar.com.unq.eis.trainup.controller.dto.LoginDTO
import ar.com.unq.eis.trainup.controller.dto.UsuarioDTO
import ar.com.unq.eis.trainup.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuario")
class UsuarioController(
    @Autowired private val usuarioService: UsuarioService
) {

    @PostMapping
    fun crearUsuario(@RequestBody usuarioDTO: UsuarioDTO): ResponseEntity<*> {
        return try {
            val usuario = usuarioService.crearUsuario(usuarioDTO.aModelo())
            ResponseEntity.ok(UsuarioDTO.desdeModelo(usuario))
        } catch (e: IllegalStateException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDTO(e))
        }
        catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO(e))
        }
    }

    @GetMapping("username/{username}")
    fun obtenerUsuarioPorUsername(@PathVariable username: String): ResponseEntity<*> {
        return try {
            val usuario = usuarioService.obtenerUsuarioPorUsername(username)
            ResponseEntity.ok(UsuarioDTO.desdeModelo(usuario))
        } catch (e: UsuarioException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDTO(e))
        }
    }

    @GetMapping("id/{id}")
    fun obtenerUsuarioPorID(@PathVariable id: String): ResponseEntity<*> {
        return try {
            val usuario = usuarioService.obtenerUsuarioPorID(id)
            ResponseEntity.ok(UsuarioDTO.desdeModelo(usuario))
        } catch (e: UsuarioException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDTO(e))
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ocurrio un error")
        }
    }

    @GetMapping
    fun obtenerUsuarios(): ResponseEntity<List<UsuarioDTO>> {
        return try {
            val usuarios = usuarioService.obtenerUsuarios()
            if (usuarios.isEmpty()) {
                ResponseEntity.status(HttpStatus.NO_CONTENT).build()
            } else {
                ResponseEntity.ok(usuarios.map { usuario -> UsuarioDTO.desdeModelo(usuario) })
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @PutMapping()
    fun actualizarUsuario(@RequestBody usuarioDTO: UsuarioDTO): ResponseEntity<*> {
        return try {
            val usuario = usuarioService.actualizarUsuario(usuarioDTO.aModelo())
            ResponseEntity.ok(UsuarioDTO.desdeModelo(usuario))
        }catch (e: UsuarioException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDTO(e))
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarUsuario(@PathVariable userId: String): ResponseEntity<*> {
        return try {
            usuarioService.eliminarUsuario(userId)
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
        } catch (e: UsuarioException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDTO(e))
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ocurrio un error")
        }
    }

    @PostMapping("/login")
    fun loguearUsuario(@RequestBody loginDTO: LoginDTO): ResponseEntity<*> {
        return try {
            val username = loginDTO.username
            val password = loginDTO.password

            if (username.isBlank() || password.isBlank()) {
                throw UsuarioException("body invalido")
            }
            val usuario = usuarioService.logIn(username, password)
            ResponseEntity.ok(UsuarioDTO.desdeModelo(usuario))
        } catch (e: UsuarioException) {
            ResponseEntity.badRequest().body(ErrorDTO(e))
        }
    }

    @PostMapping("/completarRutina/{userId}/{rutinaId}")
    fun completarRutina(@PathVariable userId: String, @PathVariable rutinaId: String):ResponseEntity<*>{
        return try {
            usuarioService.completarRutina(userId,rutinaId)
            ResponseEntity.ok("rutina completada exitosamente")
        }catch (e: RutinaException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDTO(e))
        }catch (e: UsuarioException){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDTO(e))
        }
    }
}