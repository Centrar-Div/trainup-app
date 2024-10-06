package ar.com.unq.eis.trainup.services

import ar.com.unq.eis.trainup.model.Ejercicio
import ar.com.unq.eis.trainup.model.Rutina


interface RutinaService {

    fun crearRutina(rutina: Rutina): Rutina

    fun obtenerRutinas(): List<Rutina>

    fun obtenerRutinaPorId(id: String): Rutina?

    fun actualizarRutina(id: String, rutinaActualizada: Rutina): Rutina

    fun eliminarRutina(id: String)

    fun agregarEjercicio(id: String, ejercicio: Ejercicio): Rutina

}
