package ar.com.unq.eis.trainup.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "rutinas")
class Rutina {

    @Id
    var id: String? = null
    var nombre: String
    var descripcion: String
    var categoria: String
    var fechaCreacion: String
    var ejercicios: MutableList<Ejercicio> = mutableListOf()


    // Constructor principal
    constructor(nombre: String, descripcion: String, categoria: String, ejercicios: List<Ejercicio>?) {
        if (nombre.isBlank()) {
            throw IllegalArgumentException("El nombre no puede estar vacío")
        }
        if (descripcion.isBlank()) {
            throw IllegalArgumentException("La descripción no puede estar vacía")
        }
        if (categoria.isBlank()) {
            throw IllegalArgumentException("La categoría no puede estar vacía")
        }

        this.nombre = nombre
        this.descripcion = descripcion
        this.categoria = categoria
        this.fechaCreacion = LocalDateTime.now().toString()
    }

    constructor(id: String?, nombre: String?, descripcion: String?, categoria: String?, ejercicios: List<Ejercicio>?) {
        if (nombre.isNullOrBlank()) {
            throw IllegalArgumentException("El nombre no puede ser nulo o vacío")
        }
        if (descripcion.isNullOrBlank()) {
            throw IllegalArgumentException("La descripción no puede ser nula o vacía")
        }
        if (categoria.isNullOrBlank()) {
            throw IllegalArgumentException("La categoría no puede ser nula o vacía")
        }

        this.id = id
        this.nombre = nombre
        this.descripcion = descripcion
        this.categoria = categoria
        this.fechaCreacion = LocalDateTime.now().toString()
        this.ejercicios = ejercicios?.toMutableList() ?: mutableListOf()
    }

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
        return "Rutina(id='$id', nombre='$nombre', descripcion='$descripcion', categoria='$categoria', fechaCreacion='$fechaCreacion', ejercicios=$ejercicios)"
    }
}
