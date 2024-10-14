package ar.com.unq.eis.trainup.modeloTest

import ar.com.unq.eis.trainup.controller.Exceptions.UsuarioException
import ar.com.unq.eis.trainup.model.Ejercicio
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.model.Usuario
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import kotlin.test.assertEquals

class UsuarioTest {

    private lateinit var ejercicios: MutableList<Ejercicio>
    private lateinit var rutina: Rutina
    private lateinit var usuario: Usuario

    @BeforeEach
    fun setup() {
        usuario = Usuario(
            username = "userMock",
            password = "123",
            nombre = "user mock",
            edad = 20,
            fecNacimiento = LocalDate.of(2004, 9, 12),
            telefono = "1234567890",
            genero = "masculino",
            altura = "170",
            peso = "60",
            objetivo = "ganar musculatura",
            esAdmin = false
        )

        ejercicios = mutableListOf(
            Ejercicio(
                nombre = "Sprints en cinta",
                descripcion = "Sprints a máxima velocidad",
                repeticiones = 10,
                peso = 0.0,
                musculo = "Piernas",
                series = 3,
                descansoSegundos = 30,
                equipo = "Cinta",
                instrucciones = "Corre a máxima velocidad por 30 segundos"
            ),
            Ejercicio(
                nombre = "Salto con rodillas al pecho",
                descripcion = "Salto explosivo con rodillas al pecho",
                repeticiones = 15,
                peso = 0.0,
                musculo = "Piernas",
                series = 3,
                descansoSegundos = 30,
                equipo = "Ninguno",
                instrucciones = "Salta lo más alto que puedas"
            )
        )

        rutina = Rutina(
            nombre = "Rutina HIIT",
            descripcion = "Entrenamiento de intervalos de alta intensidad",
            categoria = "HIIT",
            ejercicios = ejercicios.toMutableList()
        )

    }

    @Test
    fun `constructor del usuario es correcto`() {
        assertEquals("userMock", usuario.username)
        assertEquals("123", usuario.password)
        assertEquals("user mock", usuario.nombre)
        assertEquals(20, usuario.edad)
        assertEquals(LocalDate.of(2004, 9, 12), usuario.fecNacimiento)
        assertEquals("1234567890", usuario.telefono)
        assertEquals("masculino", usuario.genero)
        assertEquals("170", usuario.altura)
        assertEquals("60", usuario.peso)
        assertEquals("ganar musculatura", usuario.objetivo)
        assertFalse(usuario.esAdmin)  // Si esAdmin es falso por defecto
    }

    @Test
    fun `username no puede estar en blanco`() {
        val exception = assertThrows<IllegalArgumentException> {
            Usuario(
                username = "",
                password = "123",
                nombre = "user mock",
                edad = 20,
                fecNacimiento = LocalDate.of(2004, 9, 12),
                telefono = "1234567890",
                genero = "masculino",
                altura = "170",
                peso = "60",
                objetivo = "ganar musculatura"
            )
        }
        assertEquals("El username no puede estar vacío", exception.message)
    }

    @Test
    fun `password no puede estar en blanco`() {
        val exception = assertThrows<IllegalArgumentException> {
            Usuario(
                username = "userMock",
                password = "",
                nombre = "user mock",
                edad = 20,
                fecNacimiento = LocalDate.of(2004, 9, 12),
                telefono = "1234567890",
                genero = "masculino",
                altura = "170",
                peso = "60",
                objetivo = "ganar musculatura"
            )
        }
        assertEquals("La contraseña no puede estar vacía", exception.message)
    }

    @Test
    fun `nombre no puede estar en blanco`() {
        val exception = assertThrows<IllegalArgumentException> {
            Usuario(
                username = "userMock",
                password = "123",
                nombre = "",
                edad = 20,
                fecNacimiento = LocalDate.of(2004, 9, 12),
                telefono = "1234567890",
                genero = "masculino",
                altura = "170",
                peso = "60",
                objetivo = "ganar musculatura"
            )
        }
        assertEquals("El nombre no puede estar vacío", exception.message)
    }

    @Test
    fun `completar rutina exitoso`() {
        usuario.rutinasSeguidas.add(rutina)

        assertEquals(mutableListOf(rutina), usuario.rutinasSeguidas)
        assertEquals(mutableListOf<Rutina>(), usuario.rutinasCompletadas)

        usuario.completarRutina(rutina)

        assertEquals(mutableListOf(rutina), usuario.rutinasCompletadas)
        assertEquals(mutableListOf<Rutina>(), usuario.rutinasSeguidas)
    }

    @Test
    fun `completar rutina fallido`() {
        val exception = assertThrows<UsuarioException> {
            usuario.completarRutina(rutina)
        }
        assertEquals("El usuario no sigue a dicha rutina", exception.message)
    }

    @Test
    fun `seguir rutina`() {
        usuario.followUnfollowRutina(rutina)
        assertEquals(mutableListOf(rutina), usuario.rutinasSeguidas)
    }
}
