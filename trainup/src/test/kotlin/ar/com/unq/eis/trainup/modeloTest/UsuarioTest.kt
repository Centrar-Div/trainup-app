package ar.com.unq.eis.trainup.modeloTest

import ar.com.unq.eis.trainup.controller.Exceptions.UsuarioException
import ar.com.unq.eis.trainup.model.Ejercicio
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.model.Usuario
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import kotlin.test.assertEquals

class UsuarioTest {

    lateinit var ejercicios: List<Ejercicio>
    lateinit var rutina: Rutina
    lateinit var usuario: Usuario

    @BeforeEach
    fun setup() {
        usuario = Usuario(
            "userMock",
            "123",
            "user mock",
            20,
            LocalDate.of(2004, 9, 12),
            "1234567890",
            "masculino",
            "170",
            "60",
            "ganar musculatura"
        )

         ejercicios = listOf(
            Ejercicio(
                nombre = "Sprints en cinta",
                descripcion = "Sprints a máxima velocidad",
                repeticiones = 10,
                peso = 0.0,
                musculo = "Piernas"
            ),
            Ejercicio(
                nombre = "Salto con rodillas al pecho",
                descripcion = "Salto explosivo con rodillas al pecho",
                repeticiones = 15,
                peso = 0.0,
                musculo = "Piernas"
            )
        )

        rutina = Rutina(
            nombre = "Rutina HIIT",
            descripcion = "Entrenamiento de intervalos de alta intensidad",
            categoria = "HIIT",
            ejercicios = ejercicios
        )
    }

    @Test
    fun constructorTest() {
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
    }

    @Test
    fun constructorBlankUsernameTest() {
        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Usuario(
                "",
                "123",
                "user mock",
                20,
                LocalDate.of(2004, 9, 12),
                "1234567890",
                "masculino",
                "170",
                "60",
                "ganar musculatura"
            )
        }

        assertEquals("El username no puede estar vacío", exception.message)
    }

    @Test
    fun constructorBlankPassTest() {
        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Usuario(
                "userMock",
                "",
                "user mock",
                20,
                LocalDate.of(2004, 9, 12),
                "1234567890",
                "masculino",
                "170",
                "60",
                "ganar musculatura"
            )
        }

        assertEquals("La contraseña no puede estar vacía", exception.message)
    }

    @Test
    fun constructorBlankNameTest() {
        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            Usuario(
                "userMock",
                "123",
                "",
                20,
                LocalDate.of(2004, 9, 12),
                "1234567890",
                "masculino",
                "170",
                "60",
                "ganar musculatura"
            )
        }

        assertEquals("El nombre no puede estar vacío", exception.message)
    }


    @Test
    fun `completar rutina exitoso`(){

        // usuario agrega la rutina a sus seguidas. no tiene completadas

        usuario.rutinasSeguidas.add(rutina)

        assertEquals(listOf(rutina),usuario.rutinasSeguidas)
        assertEquals(emptyList(),usuario.rutinasCompletadas)

        // exercise
        usuario.completarRutina(rutina)

        // rutina pasa a sus rutinas completadas y deja de estar en seguidas
        assertEquals(listOf(rutina),usuario.rutinasCompletadas)
        assertEquals(listOf(),usuario.rutinasSeguidas)
    }


    @Test
    fun `completar rutina fallido`(){
        val exception = assertThrows<UsuarioException> {
            usuario.completarRutina(rutina)
        }
        assertEquals("El usuario no sigue a dicha rutina", exception.message)
    }

    @Test
    fun `seguir rutina`(){
        usuario.followUnfollowRutina(rutina);
        assertEquals(listOf(rutina), usuario.rutinasSeguidas)
    }


}