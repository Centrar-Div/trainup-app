package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Ejercicio

class EjercicioDTO {
    constructor(id: String?, nombre: String, descripcion: String, repeticiones: Int, peso: Double, musculo: String)

    var id: String? = null
    lateinit var nombre: String
    lateinit var descripcion: String
    var repeticiones: Int = 0
    var peso: Double = 0.0
    lateinit var musculo: String

    companion object {
        fun desdeModelo(ejercicio: Ejercicio): EjercicioDTO {
            return EjercicioDTO(
                id = ejercicio.id,
                nombre = ejercicio.nombre,
                descripcion = ejercicio.descripcion,
                repeticiones = ejercicio.repeticiones,
                peso = ejercicio.peso,
                musculo = ejercicio.musculo
            )
        }
    }


    fun aModelo(): Ejercicio {
        return Ejercicio(
            id = this.id,
            nombre = this.nombre,
            descripcion = this.descripcion,
            repeticiones = this.repeticiones,
            peso = this.peso,
            musculo = this.musculo
        )
    }
}