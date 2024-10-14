package ar.com.unq.eis.trainup.controller
import EjercicioService
import ar.com.unq.eis.trainup.controller.dto.EjercicioDTO
import ar.com.unq.eis.trainup.controller.dto.ErrorDTO
import ar.com.unq.eis.trainup.model.Ejercicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/ejercicios")
class EjercicioController(
    @Autowired private val ejercicioService: EjercicioService
) {

    @PostMapping
    fun crearEjercicio(@RequestBody ejercicioDTO: EjercicioDTO): ResponseEntity<Any> {
        return try {
            val ejercicio = Ejercicio(
                nombre = ejercicioDTO.nombre,
                descripcion = ejercicioDTO.descripcion,
                repeticiones = ejercicioDTO.repeticiones,
                peso = ejercicioDTO.peso,
                musculo = ejercicioDTO.musculo,
                series = ejercicioDTO.series,
                descansoSegundos = ejercicioDTO.descansoSegundos,
                equipo = ejercicioDTO.equipo,
                instrucciones = ejercicioDTO.instrucciones,
                completado = ejercicioDTO.completado
            )
            val nuevoEjercicio = ejercicioService.crearEjercicio(ejercicio)
            ResponseEntity.ok(
                EjercicioDTO(
                    nuevoEjercicio.id,
                    nuevoEjercicio.nombre,
                    nuevoEjercicio.descripcion,
                    nuevoEjercicio.repeticiones,
                    nuevoEjercicio.peso,
                    nuevoEjercicio.musculo,
                    nuevoEjercicio.series,
                    nuevoEjercicio.descansoSegundos,
                    nuevoEjercicio.equipo,
                    nuevoEjercicio.instrucciones,
                    nuevoEjercicio.completado
                )
            )
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO(e))
        }
    }


    @GetMapping
    fun obtenerEjercicios(): ResponseEntity<Any> {
        return try {
            val ejercicios = ejercicioService.obtenerEjercicios()
            ResponseEntity.ok(ejercicios.map { e ->
                EjercicioDTO(
                    id = e.id,
                    nombre = e.nombre,
                    descripcion = e.descripcion,
                    repeticiones = e.repeticiones,
                    peso = e.peso,
                    musculo = e.musculo
                )
            })
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO(e))
        }
    }

    @GetMapping("/{id}")
    fun obtenerEjercicioPorId(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            val ejercicio = ejercicioService.obtenerEjercicioPorId(id)
            ResponseEntity.ok(EjercicioDTO(ejercicio.id, ejercicio.nombre, ejercicio.descripcion, ejercicio.repeticiones, ejercicio.peso, ejercicio.musculo))
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDTO(e))
        }
    }

    @PutMapping("/actualizar")
    fun actualizarEjercicio(@RequestBody ejercicioDTO: EjercicioDTO): ResponseEntity<Any> {
        return try {
            val ejercicioActualizado = ejercicioDTO.aModelo()
            val ejercicio = ejercicioService.actualizarEjercicio(ejercicioActualizado.id ?: throw IllegalArgumentException("El ID no puede ser nulo"), ejercicioActualizado)
            ResponseEntity.ok(EjercicioDTO.desdeModelo(ejercicio))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO(e))
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarEjercicio(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            ejercicioService.eliminarEjercicio(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDTO(e))
        }
    }
}
