package ar.com.unq.eis.trainup.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "rutinas")

class Rutina{
    @Id
    var id: String? = null
    var nombre: String? = null
    var ejercicios: MutableList<Ejercicio>  = mutableListOf()

    constructor(nombre: String)


    fun agregarEjercicio(ejercicio: Ejercicio) {
        ejercicios.add(ejercicio)
    }

    fun eliminarEjercicio(ejercicio: Ejercicio) {
        ejercicios.remove(ejercicio)
    }

    fun listarEjercicios(): List<Ejercicio> {
        return ejercicios
    }

    override fun toString(): String {
        return "Rutina(nombre='$nombre', ejercicios=$ejercicios)"
    }
}