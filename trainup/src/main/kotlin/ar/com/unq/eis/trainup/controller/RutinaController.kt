package ar.com.unq.eis.trainup.controller

import ar.com.unq.eis.trainup.controller.dto.EjercicioDTO
import ar.com.unq.eis.trainup.controller.dto.ErrorDTO
import ar.com.unq.eis.trainup.controller.dto.RutinaDTO
import ar.com.unq.eis.trainup.model.Ejercicio
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.services.RutinaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rutinas")
class RutinaController(
    @Autowired private val rutinaService: RutinaService
) {

    @PostMapping
    fun crearRutina(@RequestBody rutinaDTO: RutinaDTO): ResponseEntity<Any> {
        return try {
            val rutina = Rutina(
                nombre = rutinaDTO.nombre,
                descripcion = rutinaDTO.descripcion,
                categoria = rutinaDTO.categoria,
                ejercicios = rutinaDTO.ejercicios.map {
                    Ejercicio(
                        id = it.id,
                        nombre = it.nombre,
                        descripcion = it.descripcion,
                        repeticiones = it.repeticiones,
                        peso = it.peso,
                        musculo = it.musculo
                    )
                }.toMutableList()
            )
            val nuevaRutina = rutinaService.crearRutina(rutina)
            ResponseEntity.ok(
                RutinaDTO(
                    nuevaRutina.id,
                    nuevaRutina.nombre,
                    nuevaRutina.descripcion,
                    nuevaRutina.categoria,
                    nuevaRutina.fechaCreacion,
                    nuevaRutina.ejercicios.map {
                        EjercicioDTO(it.id, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
                    }.toMutableList()
                )
            )
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO(e))
        }
    }

    @GetMapping
    fun obtenerRutinas(): ResponseEntity<Any> {
        return try {
            val rutinas = rutinaService.obtenerRutinas()
            ResponseEntity.ok(rutinas.map { rutina ->
                RutinaDTO(
                    rutina.id,
                    rutina.nombre,
                    rutina.descripcion,
                    rutina.categoria,
                    rutina.fechaCreacion ?: "",
                    rutina.ejercicios.map {
                        EjercicioDTO(it.id, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
                    }.toMutableList()
                )
            })
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO(e))
        }
    }

    @GetMapping("/{id}")
    fun obtenerRutinaPorId(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            val rutina = rutinaService.obtenerRutinaPorId(id)
            if (rutina != null) {
                ResponseEntity.ok(
                    RutinaDTO(
                        rutina.id,
                        rutina.nombre,
                        rutina.descripcion,
                        rutina.categoria,
                        rutina.fechaCreacion ?: "",
                        rutina.ejercicios.map {
                            EjercicioDTO(it.id, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
                        }.toMutableList()
                    )
                )
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDTO(Exception("Rutina no encontrada")))
            }
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDTO(e))
        }
    }

    @PutMapping("/{id}")
    fun actualizarRutina(@PathVariable id: String, @RequestBody rutinaDTO: RutinaDTO): ResponseEntity<Any> {
        return try {
            val rutinaActualizada = Rutina(
                id = id,
                nombre = rutinaDTO.nombre,
                descripcion = rutinaDTO.descripcion,
                categoria = rutinaDTO.categoria,
                ejercicios = rutinaDTO.ejercicios.map {
                    Ejercicio(it.id, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
                }.toMutableList()
            )
            val rutina = rutinaService.actualizarRutina(id, rutinaActualizada)
            ResponseEntity.ok(
                RutinaDTO(
                    rutina.id,
                    rutina.nombre,
                    rutina.descripcion,
                    rutina.categoria,
                    rutina.fechaCreacion ?: "",
                    rutina.ejercicios.map {
                        EjercicioDTO(it.id, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
                    }.toMutableList()
                )
            )
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDTO(e))
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarRutina(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            rutinaService.eliminarRutina(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDTO(e))
        }
    }
}
