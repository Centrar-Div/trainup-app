package ar.com.unq.eis.trainup.controller

import EjercicioService
import ar.com.unq.eis.trainup.controller.dto.EjercicioDTO
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
    fun crearEjercicio(@RequestBody ejercicioDTO: EjercicioDTO): ResponseEntity<EjercicioDTO> {
        return try {
            val ejercicio = Ejercicio(
                nombre = ejercicioDTO.nombre,
                descripcion = ejercicioDTO.descripcion,
                repeticiones = ejercicioDTO.repeticiones,
                peso = ejercicioDTO.peso,
                musculo = ejercicioDTO.musculo
            )
            val nuevoEjercicio = ejercicioService.crearEjercicio(ejercicio)
            ResponseEntity.ok(EjercicioDTO(nuevoEjercicio.id, nuevoEjercicio.nombre, nuevoEjercicio.descripcion, nuevoEjercicio.repeticiones, nuevoEjercicio.peso, nuevoEjercicio.musculo))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping
    fun obtenerEjercicios(): ResponseEntity<List<EjercicioDTO>> {
        return try {
            val ejercicios = ejercicioService.obtenerEjercicios()
            ResponseEntity.ok(ejercicios.map { ejercicio ->
                EjercicioDTO(ejercicio.id, ejercicio.nombre, ejercicio.descripcion, ejercicio.repeticiones, ejercicio.peso, ejercicio.musculo)
            })
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @GetMapping("/{id}")
    fun obtenerEjercicioPorId(@PathVariable id: String): ResponseEntity<EjercicioDTO> {
        return try {
            val ejercicio = ejercicioService.obtenerEjercicioPorId(id)
            ResponseEntity.ok(EjercicioDTO(ejercicio.id, ejercicio.nombre, ejercicio.descripcion, ejercicio.repeticiones, ejercicio.peso, ejercicio.musculo))
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @PutMapping("/{id}")
    fun actualizarEjercicio(@PathVariable id: String, @RequestBody ejercicioDTO: EjercicioDTO): ResponseEntity<EjercicioDTO> {
        return try {
            val ejercicioActualizado = Ejercicio(
                nombre = ejercicioDTO.nombre,
                descripcion = ejercicioDTO.descripcion,
                repeticiones = ejercicioDTO.repeticiones,
                peso = ejercicioDTO.peso,
                musculo = ejercicioDTO.musculo
            )
            val ejercicio = ejercicioService.actualizarEjercicio(id, ejercicioActualizado)
            ResponseEntity.ok(EjercicioDTO(ejercicio.id, ejercicio.nombre, ejercicio.descripcion, ejercicio.repeticiones, ejercicio.peso, ejercicio.musculo))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarEjercicio(@PathVariable id: String ): ResponseEntity<Void> {
        return try {
            ejercicioService.eliminarEjercicio(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}