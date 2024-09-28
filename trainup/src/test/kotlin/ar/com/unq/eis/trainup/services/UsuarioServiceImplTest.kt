package ar.com.unq.eis.trainup.services

import ar.com.unq.eis.trainup.controller.Exceptions.RutinaException
import ar.com.unq.eis.trainup.controller.Exceptions.UsuarioException
import ar.com.unq.eis.trainup.model.Ejercicio
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.model.Usuario
import ar.com.unq.eis.trainup.services.impl.UsuarioServiceImpl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
class UsuarioServiceImplTest {

    private lateinit var usuario: Usuario
    private lateinit var rutina: Rutina

    private lateinit var usuarioCreado: Usuario

    @Autowired
    private lateinit var usuarioService: UsuarioServiceImpl;

    @Autowired
    private lateinit var rutinaService: RutinaService;

    @BeforeEach
    fun setup() {
        usuario = Usuario(
            "userTest",
            "123",
            "user test",
            20,
            LocalDate.of(2004, 9, 12),
            "1234567890",
            "masculino",
            "170",
            "60",
            "ganar musculatura"
        )

        val ejercicios = listOf(
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

        rutina = rutinaService.crearRutina(
            Rutina(
                nombre = "Rutina HIIT",
                descripcion = "Entrenamiento de intervalos de alta intensidad",
                categoria = "HIIT",
                ejercicios = ejercicios
            )
        )

        usuarioCreado = usuarioService.crearUsuario(usuario)


    }

    @Test
    fun `crear Usuario Test`() {
        assertNotNull(usuarioCreado.id)
        assertEquals(usuario, usuarioCreado)
    }

    @Test
    fun `crear Usuario username repetido Test`() {
        val exception = assertThrows(UsuarioException::class.java) {
            usuarioService.crearUsuario(usuario)
        }
        assertEquals("Ya existe un usuario con username: userTest", exception.message)
    }

    @Test
    fun `obtener usuario por id existente test`() {
        val usuarioObtenido = usuarioService.obtenerUsuarioPorID(usuarioCreado.id!!)
        assertEquals(usuarioCreado, usuarioObtenido)
    }

    @Test
    fun `obtener usuario por id inexistente test`() {
        val exception = assertThrows(UsuarioException::class.java) {
            usuarioService.obtenerUsuarioPorID("1")
        }
        assertEquals("No existe usuario con id 1", exception.message)
    }

    @Test
    fun `obtener usuario por username existente test`() {
        val usuarioObtenido = usuarioService.obtenerUsuarioPorUsername(usuarioCreado.username)
        assertEquals(usuarioCreado, usuarioObtenido)
    }

    @Test
    fun `obtener usuario por username inexistente test`() {
        val exception = assertThrows(UsuarioException::class.java) {
            usuarioService.obtenerUsuarioPorUsername("pepe")
        }
        assertEquals("No existe usuario pepe", exception.message)
    }

    // @Test
    fun `obtener usuarios test`() {
        val usuariosObtenidos = usuarioService.obtenerUsuarios()
        assertEquals(listOf(usuarioCreado), usuariosObtenidos)
    }

    @Test
    fun `actualizar usuario test`() {
        usuarioCreado.edad = 30;
        usuarioService.actualizarUsuario(usuarioCreado)
        assertEquals(30, usuarioService.obtenerUsuarioPorID(usuarioCreado.id!!).edad)
    }

    @Test
    fun `actualizar usuario inexistente test`() {

        val exception = assertThrows(UsuarioException::class.java) {
            val user = Usuario(
                "x",
                "x",
                "x",
                20,
                LocalDate.of(2004, 9, 12),
                "x",
                "x",
                "x",
                "x",
                "x"
            )
            user.id = "1"
            usuarioService.actualizarUsuario(user)
        }

        assertEquals("No existe usuario con id 1", exception.message)
    }

    @Test
    fun `logIn exitoso test`() {
        val logueado = usuarioService.logIn("userTest", "123")
        assertEquals(usuarioCreado, logueado)
    }

    @Test
    fun `logIn fallido test`() {
        val exceptionName = assertThrows(UsuarioException::class.java) {
            usuarioService.logIn("aaa", "123")
        }
        val exceptionPass = assertThrows(UsuarioException::class.java) {
            usuarioService.logIn("userTest", "5555")
        }
        assertEquals("usuario no encontrado", exceptionName.message)
        assertEquals("usuario no encontrado", exceptionPass.message)
    }

    @Test
    fun `eliminar fallido test`() {
        val exception = assertThrows(UsuarioException::class.java) {
            usuarioService.eliminarUsuario("1")
        }
        assertEquals("No existe usuario con id 1", exception.message)

    }


    @Test
    fun `completar rutina inexistente`() {
        val exception = assertThrows<RutinaException> {
            usuarioService.completarRutina(usuarioCreado.id!!, "rutina inexistente")
        }
        assertEquals("No existe rutina con id rutina inexistente", exception.message)
    }

    @Test
    fun `completar rutina usuario inexistente`() {
        val exception = assertThrows<UsuarioException> {
            usuarioService.completarRutina("usuario inexistente", rutina.id!!)
        }
        assertEquals("No existe usuario con id usuario inexistente", exception.message)
    }

    @Test
    fun `completar rutina no seguida`() {
        val exception = assertThrows<UsuarioException> {
            usuarioService.completarRutina(usuarioCreado.id!!, rutina.id!!)
        }
        assertEquals("El usuario userTest no sigue a rutina id: ${rutina.id}", exception.message)
    }


    @Test
    fun `completar rutina exitoso`() {

        assertEquals(emptyList(), usuarioCreado.rutinasCompletadas)

        usuarioCreado.rutinasSeguidas.add(rutina)
        usuarioService.actualizarUsuario(usuarioCreado)

        usuarioService.completarRutina(usuarioCreado.id!!, rutina.id!!)

        val userTest = usuarioService.obtenerUsuarioPorUsername("userTest")

        assertEquals(listOf(rutina), userTest.rutinasCompletadas)

    }


    @Test
    fun `seguir rutina`(){

        val user = usuarioService.updateFollowRutina(usuarioCreado.id!!,rutina.id!!)
        val rutinasSeguidas = user.rutinasSeguidas
        assertEquals(user.rutinasSeguidas.size, 1)
        assertEquals(listOf(rutina),rutinasSeguidas)
    }

    @Test
    fun `dejar de seguir rutina`(){
        //sigo la rutina
        usuarioService.updateFollowRutina(usuarioCreado.id!!,rutina.id!!)
        assertEquals(usuarioCreado.rutinasSeguidas.size, 0)

        val user = usuarioService.updateFollowRutina(usuarioCreado.id!!,rutina.id!!)
        assertEquals(user.rutinasSeguidas.size, 0)
        assertEquals(emptyList(),user.rutinasSeguidas)
    }


    @AfterEach
    fun `borrar instancia`() {
        usuarioService.eliminarUsuario(usuarioCreado.id!!)
    }

}