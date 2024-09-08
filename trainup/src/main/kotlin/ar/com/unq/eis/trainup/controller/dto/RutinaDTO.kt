package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Rutina

class RutinaDTO {
    constructor()
    constructor(id: String?, nombre: String?, map: List<EjercicioDTO>)

    var id: String? = null
    var nombre: String? = null
    var ejercicios: MutableList<EjercicioDTO>? = null

    companion object {
        fun desdeModelo(rutina: Rutina): RutinaDTO {
            val dto = RutinaDTO()
            dto.id = rutina.id
            dto.nombre = rutina.nombre
            dto.ejercicios = rutina.ejercicios.map { EjercicioDTO.desdeModelo(it) }.toCollection(mutableListOf())
            return dto
        }
    }


    fun aModelo(): Rutina {
        val rutina = Rutina()
        rutina.id = this.id
        rutina.nombre = this.nombre
        rutina.ejercicios = this.ejercicios!!.map { it.aModelo() }.toCollection(mutableListOf())
        return rutina
    }
}
