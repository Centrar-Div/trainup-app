package ar.com.unq.eis.trainup.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document(collection = "rutinas")
class Rutina {

    @Id
    var id: String? = null
    var nombre: String = ""
    var descripcion: String = ""
    var categoria: String = ""
    var fechaCreacion: String = LocalDateTime.now().toString()
    var ejercicios: MutableList<Ejercicio> = mutableListOf()

    // Constructor vacío para MongoDB
    constructor()

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
        this.ejercicios = ejercicios?.toMutableList() ?: mutableListOf()
        this.fechaCreacion = LocalDateTime.now().toString()
    }

    constructor(id: String?, nombre: String, descripcion: String, categoria: String, ejercicios: List<Ejercicio>?) {
        if (nombre.isBlank()) {
            throw IllegalArgumentException("El nombre no puede estar vacío")
        }
        if (descripcion.isBlank()) {
            throw IllegalArgumentException("La descripción no puede estar vacía")
        }
        if (categoria.isBlank()) {
            throw IllegalArgumentException("La categoría no puede estar vacía")
        }

        this.id = id
        this.nombre = nombre
        this.descripcion = descripcion
        this.categoria = categoria
        this.ejercicios = ejercicios?.toMutableList() ?: mutableListOf()
        this.fechaCreacion = LocalDateTime.now().toString()
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val rutina = other as Rutina?
        return id == rutina!!.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}