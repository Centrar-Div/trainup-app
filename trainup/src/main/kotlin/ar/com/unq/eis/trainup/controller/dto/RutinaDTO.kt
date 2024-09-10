package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Rutina

data class RutinaDTO(
    var id: String? = null,
    var nombre: String = "",
    var descripcion: String = "",
    var categoria: String = "",
    var fechaCreacion: String = "",
    var ejercicios: MutableList<EjercicioDTO> = mutableListOf()
) {

    companion object {
        fun desdeModelo(rutina: Rutina): RutinaDTO {
            return RutinaDTO(
                id = rutina.id,
                nombre = rutina.nombre,
                descripcion = rutina.descripcion,
                categoria = rutina.categoria,
                fechaCreacion = rutina.fechaCreacion,
                ejercicios = rutina.ejercicios.map { EjercicioDTO.desdeModelo(it) }.toMutableList()
            )
        }
    }

    fun aModelo(): Rutina {
        return Rutina(
            id = this.id,
            nombre = this.nombre,
            descripcion = this.descripcion,
            categoria = this.categoria,
            ejercicios = this.ejercicios.map { it.aModelo() }
        ).also {
            it.fechaCreacion = this.fechaCreacion
        }
    }
}
