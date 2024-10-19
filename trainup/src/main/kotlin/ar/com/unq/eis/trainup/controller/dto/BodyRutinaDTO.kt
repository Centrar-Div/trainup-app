package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Rutina

class BodyRutinaDTO(
    var id: String? = null,
    var nombre: String = "",
    var descripcion: String = "",
    var categoria: String = "",
    var dificultad: String = ""
) {
    fun aModelo(): Rutina {

        val rutina = Rutina(
            nombre = this.nombre,
            descripcion = this.descripcion,
            categoria = this.categoria,
            ejercicios = mutableListOf(),
            dificultad = this.dificultad
        )
        rutina.id = this.id;
        return rutina
    }
}
