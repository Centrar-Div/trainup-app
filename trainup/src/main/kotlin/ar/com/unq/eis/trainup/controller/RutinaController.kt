package ar.com.unq.eis.trainup.controller

import ar.com.unq.eis.trainup.controller.dto.EjercicioDTO
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
    fun crearRutina(@RequestBody rutinaDTO: RutinaDTO): ResponseEntity<RutinaDTO> {
        return try {
            val rutina = Rutina(
                id = rutinaDTO.id ?: 0,
                nombre = rutinaDTO.nombre,
                ejercicios = rutinaDTO.ejercicios?.map {
                    Ejercicio(
                        id = it.id ?: 0,
                        nombre = it.nombre,
                        descripcion = it.descripcion,
                        repeticiones = it.repeticiones,
                        peso = it.peso,
                        musculo = it.musculo
                    )
                }
            )
            val nuevaRutina = rutinaService.crearRutina(rutina)
            ResponseEntity.ok(RutinaDTO(nuevaRutina.id, nuevaRutina.nombre, nuevaRutina.ejercicios.map {
                EjercicioDTO(it.id, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
            }))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @GetMapping
    fun obtenerRutinas(): ResponseEntity<List<RutinaDTO>> {
        return try {
            val rutinas = rutinaService.obtenerRutinas()
            ResponseEntity.ok(rutinas.map { rutina ->
                RutinaDTO(rutina.id, rutina.nombre, rutina.ejercicios.map {
                    EjercicioDTO(it.id, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
                })
            })
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @GetMapping("/{id}")
    fun obtenerRutinaPorId(@PathVariable id: String): ResponseEntity<RutinaDTO> {
        return try {
            val rutina = rutinaService.obtenerRutinaPorId(id)
            ResponseEntity.ok(RutinaDTO(rutina?.id, rutina?.nombre, rutina?.ejercicios!!.map {
                EjercicioDTO(it.id, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
            }))
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @PutMapping("/{id}")
    fun actualizarRutina(@PathVariable id: String, @RequestBody rutinaDTO: RutinaDTO): ResponseEntity<RutinaDTO> {
        return try {
            val rutinaActualizada = Rutina(
                id = id,
                nombre = rutinaDTO.nombre,
                ejercicios = rutinaDTO.ejercicios?.map {
                    Ejercicio(it.id ?: 0, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
                }
            )
            val rutina = rutinaService.actualizarRutina(id, rutinaActualizada)
            ResponseEntity.ok(RutinaDTO(rutina.id, rutina.nombre, rutina.ejercicios.map {
                EjercicioDTO(it.id, it.nombre, it.descripcion, it.repeticiones, it.peso, it.musculo)
            }))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarRutina(@PathVariable id: String): ResponseEntity<Void> {
        return try {
            rutinaService.eliminarRutina(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}