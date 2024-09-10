package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Rutina

class RutinaDTO {

    var id: String? = null
    var nombre: String? = null
    var descripcion: String? = null
    var categoria: String? = null
    var fechaCreacion: String? = null
    var ejercicios: MutableList<EjercicioDTO>? = null

    constructor()

    constructor(id: String?, nombre: String?, descripcion: String?, categoria: String?, fechaCreacion: String?, ejercicios: List<EjercicioDTO>?) {
        this.id = id
        this.nombre = nombre
        this.descripcion = descripcion
        this.categoria = categoria
        this.fechaCreacion = fechaCreacion
        this.ejercicios = ejercicios?.toMutableList()
    }

    companion object {
        fun desdeModelo(rutina: Rutina): RutinaDTO {
            val dto = RutinaDTO()
            dto.id = rutina.id
            dto.nombre = rutina.nombre
            dto.descripcion = rutina.descripcion
            dto.categoria = rutina.categoria
            dto.fechaCreacion = rutina.fechaCreacion
            dto.ejercicios = rutina.ejercicios.map { EjercicioDTO.desdeModelo(it) }.toCollection(mutableListOf())
            return dto
        }
    }

    fun aModelo(): Rutina {
        val rutina = Rutina(
            id = this.id,
            nombre = this.nombre,
            descripcion = this.descripcion,
            categoria = this.categoria,
            ejercicios = this.ejercicios?.map { it.aModelo() }
        )
        rutina.fechaCreacion = this.fechaCreacion ?: ""
        return rutina
    }
}
