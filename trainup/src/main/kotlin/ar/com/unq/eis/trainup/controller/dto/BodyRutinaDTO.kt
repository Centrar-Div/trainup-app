package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Rutina

class BodyRutinaDTO(
    var nombre: String = "",
    var descripcion: String = "",
    var categoria: String = ""
) {
    fun aModelo(): Rutina {
        val rutina = Rutina(this.nombre, this.descripcion, this.categoria, mutableListOf())

        return rutina
    }
}