package ar.com.unq.eis.trainup.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "usuarios")
class Usuario() {

    @Id
    var id: String? = null;
    var username: String = "";
    var password: String = "";
    var rutinasSeguidas: MutableList<Rutina> = mutableListOf();
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
        require(telefono.isNotBlank()) { "El telefono no puede estar vacío" }
        require(genero.isNotBlank()) { "El genero no puede estar vacío" }
        require(altura.isNotBlank()) { "La altura no puede estar vacía" }
        require(peso.isNotBlank()) { "El peso no puede estar vacío" }

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


}
