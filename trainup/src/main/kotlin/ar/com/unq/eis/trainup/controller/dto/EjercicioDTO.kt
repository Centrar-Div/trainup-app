package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Ejercicio

data class EjercicioDTO(
    var id: String? = null,
    var nombre: String = "",
    var descripcion: String = "",
    var repeticiones: Int = 0,
    var peso: Double = 0.0,
    var musculo: String = "",
    var series: Int = 0,
    var descansoSegundos: Int = 0,
    var equipo: String = "",
    var instrucciones: String = "",
    var completado: Boolean = false
) {

    companion object {
        fun desdeModelo(ejercicio: Ejercicio): EjercicioDTO {
            return EjercicioDTO(
                id = ejercicio.id,
                nombre = ejercicio.nombre,
                descripcion = ejercicio.descripcion,
                repeticiones = ejercicio.repeticiones,
                peso = ejercicio.peso,
                musculo = ejercicio.musculo,
                series = ejercicio.series,
                descansoSegundos = ejercicio.descansoSegundos,
                equipo = ejercicio.equipo,
                instrucciones = ejercicio.instrucciones,
                completado = ejercicio.completado
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
            musculo = this.musculo,
            series = this.series,
            descansoSegundos = this.descansoSegundos,
            equipo = this.equipo,
            instrucciones = this.instrucciones,
            completado = this.completado
        )
    }
}
