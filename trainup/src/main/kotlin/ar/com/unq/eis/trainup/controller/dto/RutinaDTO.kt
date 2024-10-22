package ar.com.unq.eis.trainup.controller.dto

import ar.com.unq.eis.trainup.model.Rutina
import java.time.LocalDateTime

data class RutinaDTO(
    var id: String? = null,
    var nombre: String = "",
    var img: String?=null,
    var descripcion: String = "",
    var categoria: String = "",
    var dificultad: String = "",
    var duracionMinutos: Int = 0,
    var objetivo: String = "",
    var frecuenciaSemanal: Int = 0,
    var fechaCreacion: String = "",
    var ejercicios: MutableList<EjercicioDTO> = mutableListOf()
) {

    companion object {
        fun desdeModelo(rutina: Rutina): RutinaDTO {
            return RutinaDTO(
                id = rutina.id,
                nombre = rutina.nombre,
                img = rutina.img,
                descripcion = rutina.descripcion,
                categoria = rutina.categoria,
                dificultad = rutina.dificultad,
                duracionMinutos = rutina.duracionMinutos,
                objetivo = rutina.objetivo,
                frecuenciaSemanal = rutina.frecuenciaSemanal,
                fechaCreacion = rutina.fechaCreacion.toString(),
                ejercicios = rutina.ejercicios.map { EjercicioDTO.desdeModelo(it) }.toMutableList()
            )
        }
    }

    fun aModelo(): Rutina {
        return Rutina(
            id = this.id,
            nombre = this.nombre,
            img= this.img,
            descripcion = this.descripcion,
            categoria = this.categoria,
            dificultad = this.dificultad,
            duracionMinutos = this.duracionMinutos,
            objetivo = this.objetivo,
            frecuenciaSemanal = this.frecuenciaSemanal,
            ejercicios = this.ejercicios.map { it.aModelo() }.toMutableList()
        ).also {
            it.fechaCreacion = LocalDateTime.parse(this.fechaCreacion)
        }
    }

}
