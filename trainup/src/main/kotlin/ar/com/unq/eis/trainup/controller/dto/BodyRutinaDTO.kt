package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Rutina

class BodyRutinaDTO(
    var nombre: String = "",
    var descripcion: String = "",
    var categoria: String = ""
) {
    fun aModelo(): Rutina {
        return Rutina(
            nombre = this.nombre,
            descripcion = this.descripcion,
            categoria = this.categoria,
            ejercicios = mutableListOf()
        )
    }
}
