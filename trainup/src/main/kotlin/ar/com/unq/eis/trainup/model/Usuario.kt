package ar.com.unq.eis.trainup.model

import ar.com.unq.eis.trainup.controller.Exceptions.UsuarioException
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.util.*

@Document(collection = "usuarios")
class Usuario() {

    @Id
    var id: String? = null;

    @Indexed(unique = true)  // Marca este campo como unico (Casi como PK)
    var username: String = "";

    var password: String = "";
    var rutinasSeguidas: MutableList<Rutina> = mutableListOf();
    var rutinasCompletadas: MutableList<Rutina> = mutableListOf();
    var nombre: String = "";
    var edad: Int? = null;
    var fecNacimiento: LocalDate? = null;
    var telefono: String = "";
    var genero: String = "";
    var altura: String = "";
    var peso: String = "";
    var objetivo: String = "";

    constructor(username: String,
                password: String,
                nombre:String,
                edad:Int,
                fecNacimiento:LocalDate,
                telefono:String,
                genero: String,
                altura:String,
                peso:String,
                objetivo:String,
        )
            : this() {
        require(username.isNotBlank()) { "El username no puede estar vacío" }
        require(password.isNotBlank()) { "La contraseña no puede estar vacía" }
        require(nombre.isNotBlank()) { "El nombre no puede estar vacío" }

        // pueden ser vacios?
//        require(telefono.isNotBlank()) { "El telefono no puede estar vacío" }
//        require(genero.isNotBlank()) { "El genero no puede estar vacío" }
//        require(altura.isNotBlank()) { "La altura no puede estar vacía" }
//        require(peso.isNotBlank()) { "El peso no puede estar vacío" }

        this.username = username;
        this.password = password;
        this.nombre = nombre
        this.edad = edad
        this.fecNacimiento = fecNacimiento
        this.telefono = telefono
        this.genero = genero
        this.altura = altura
        this.peso = peso
        this.objetivo=objetivo
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val usuario = other as Usuario?
        return id == usuario!!.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }


    fun completarRutina(rutina: Rutina) {
        if (rutina !in rutinasSeguidas) {
            throw UsuarioException("El usuario no sigue a dicha rutina")
        }
        rutinasSeguidas.remove(rutina)
        rutinasCompletadas.add(rutina)
    }

    fun followUnfollowRutina(rutina: Rutina) {
        if (!rutinasSeguidas.remove(rutina)) {
            rutinasSeguidas.add(rutina)
        }

    }


}
