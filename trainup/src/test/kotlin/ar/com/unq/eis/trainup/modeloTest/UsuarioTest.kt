package ar.com.unq.eis.trainup.modeloTest

import ar.com.unq.eis.trainup.model.Usuario
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class UsuarioTest {

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
}