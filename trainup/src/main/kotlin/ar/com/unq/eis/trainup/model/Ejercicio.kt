package ar.com.unq.eis.trainup.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ejercicios")
class Ejercicio {

    @Id
    var id: String? = null
    var nombre: String = ""
    var descripcion: String = ""
    var repeticiones: Int = 0
    var peso: Double = 0.0
    var musculo: String = ""


    constructor(
        id: String? = null,
        nombre: String,
        descripcion: String,
        repeticiones: Int,
        peso: Double,
        musculo: String
    ) {
        this.id = id
        this.nombre = nombre
        this.descripcion = descripcion
        this.repeticiones = repeticiones
        this.peso = peso
        this.musculo = musculo
    }

    override fun toString(): String {
        return "Ejercicio(id='$id', nombre='$nombre', descripcion='$descripcion', repeticiones=$repeticiones, peso=$peso, musculo='$musculo')"
    }
}