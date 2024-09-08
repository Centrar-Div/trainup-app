package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Ejercicio

class EjercicioDTO {
    constructor()
    constructor(id: String?, nombre: String, descripcion: String, repeticiones: Int, peso: Double, musculo: String)


    var id: String? = null
    var nombre: String? = null
    var descripcion: String? = null
    var repeticiones: Int? = null
    var peso: Double? = null
    var musculo: String? = null

    companion object {
        fun desdeModelo(ejercicio: Ejercicio): EjercicioDTO {
            val dto = EjercicioDTO()
            dto.id = ejercicio.id
            dto.nombre = ejercicio.nombre
            dto.descripcion = ejercicio.descripcion
            dto.repeticiones = ejercicio.repeticiones
            dto.peso = ejercicio.peso
            dto.musculo = ejercicio.musculo
            return dto
        }
    }


    fun aModelo(): Ejercicio {
        val ejercicio = Ejercicio()
        ejercicio.id = this.id
        ejercicio.nombre = this.nombre!!
        ejercicio.descripcion = this.descripcion!!
        ejercicio.repeticiones = this.repeticiones!!
        ejercicio.peso = this.peso!!
        ejercicio.musculo = this.musculo!!
        return ejercicio
    }
}