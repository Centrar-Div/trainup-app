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
    var completado: Boolean = false // Nuevo atributo

    constructor(
        id: String? = null,
        nombre: String,
        descripcion: String,
        repeticiones: Int,
        peso: Double,
        musculo: String,
        completado: Boolean = false
    ) {
        this.id = id

        // Validaciones
        if (nombre.isBlank()) {
            throw IllegalArgumentException("El nombre no puede estar vacío")
        }
        if (descripcion.isBlank()) {
            throw IllegalArgumentException("La descripción no puede estar vacía")
        }
        if (repeticiones <= 0) {
            throw IllegalArgumentException("Las repeticiones deben ser mayores a 0")
        }
        if (peso < 0) {
            throw IllegalArgumentException("El peso no puede ser negativo")
        }
        if (musculo.isBlank()) {
            throw IllegalArgumentException("El nombre del músculo no puede estar vacío")
        }

        this.nombre = nombre
        this.descripcion = descripcion
        this.repeticiones = repeticiones
        this.peso = peso
        this.musculo = musculo
        this.completado = completado
    }

    override fun toString(): String {
        return "Ejercicio(id='$id', nombre='$nombre', descripcion='$descripcion', repeticiones=$repeticiones, peso=$peso, musculo='$musculo', completado=$completado)"
    }
}