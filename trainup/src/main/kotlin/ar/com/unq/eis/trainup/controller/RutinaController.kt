package ar.com.unq.eis.trainup.controller

import ar.com.unq.eis.trainup.controller.dto.BodyRutinaDTO
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
    fun crearRutina(@RequestBody bodyRutinaDTO: BodyRutinaDTO): ResponseEntity<Any> {

        try {
            return rutinaService.crearRutina(bodyRutinaDTO.aModelo()).let {
                ResponseEntity.status(HttpStatus.CREATED).body(RutinaDTO.desdeModelo(it))
            }
        } catch (e: Exception ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDTO(Exception("El body de la rutina no es valido")))
        }

    }

    @GetMapping
    fun obtenerRutinas(): ResponseEntity<Any> {
        return rutinaService.obtenerRutinas().let { it ->
            ResponseEntity.ok(it.map { RutinaDTO.desdeModelo(it) })
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
    fun actualizarRutina(@PathVariable id: String, @RequestBody bodyRutinaDTO: BodyRutinaDTO): ResponseEntity<Any> {
        try {
            val rutinaAActualizar = rutinaService.obtenerRutinaPorId(id)!!
            rutinaAActualizar.nombre = bodyRutinaDTO.nombre
            rutinaAActualizar.descripcion = bodyRutinaDTO.descripcion
            rutinaAActualizar.categoria = bodyRutinaDTO.categoria

            return rutinaService.actualizarRutina(id, rutinaAActualizar).let {
                ResponseEntity.status(HttpStatus.OK).body(RutinaDTO.desdeModelo(it))
            }

        } catch (e: Exception ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDTO(Exception(e)))
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
