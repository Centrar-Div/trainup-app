package ar.com.unq.eis.trainup.services

import ar.com.unq.eis.trainup.controller.Exceptions.UsuarioException
import ar.com.unq.eis.trainup.model.Usuario
import ar.com.unq.eis.trainup.services.impl.UsuarioServiceImpl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import kotlin.test.AfterTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
class UsuarioServiceImplTest {

    private lateinit var usuario: Usuario

    private lateinit var usuarioCreado: Usuario

    @Autowired
    private lateinit var usuarioService : UsuarioServiceImpl;

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

        usuarioCreado = usuarioService.crearUsuario(usuario)
    }

    @Test
    fun `crear Usuario Test`(){
        assertNotNull(usuarioCreado.id)
        assertEquals(usuario, usuarioCreado)
    }

    @Test
    fun `crear Usuario username repetido Test`(){
        val exception = assertThrows(UsuarioException::class.java){
            usuarioService.crearUsuario(usuario)
        }
        assertEquals("Ya existe un usuario con username: userMock", exception.message)
    }
    @Test
    fun `obtener usuario por id existente test`(){
        val usuarioObtenido = usuarioService.obtenerUsuarioPorID(usuarioCreado.id!!)
        assertEquals(usuarioCreado, usuarioObtenido)
    }

    @Test
    fun `obtener usuario por id inexistente test`(){
        val exception = assertThrows(UsuarioException::class.java){
            usuarioService.obtenerUsuarioPorID("1")
        }
        assertEquals("No existe usuario con id 1", exception.message)
    }

    @Test
    fun `obtener usuario por username existente test`(){
        val usuarioObtenido = usuarioService.obtenerUsuarioPorUsername(usuarioCreado.username)
        assertEquals(usuarioCreado, usuarioObtenido)
    }

    @Test
    fun `obtener usuario por username inexistente test`(){
        val exception = assertThrows(UsuarioException::class.java){
            usuarioService.obtenerUsuarioPorUsername("pepe")
        }
        assertEquals("No existe usuario pepe", exception.message)
    }

    @Test
    fun `obtener usuarios test`(){
        val usuariosObtenidos = usuarioService.obtenerUsuarios()
        assertEquals(listOf(usuarioCreado), usuariosObtenidos)
    }

    @Test
    fun `actualizar usuario test`(){
        usuarioCreado.edad = 30;
        usuarioService.actualizarUsuario(usuarioCreado)
        assertEquals(30, usuarioService.obtenerUsuarioPorID(usuarioCreado.id!!).edad)
    }

    @Test
    fun `actualizar usuario inexistente test`(){

        val exception = assertThrows(UsuarioException::class.java){
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
    fun `logIn exitoso test`(){
        val logueado = usuarioService.logIn("userMock","123")
        assertEquals(usuarioCreado, logueado)
    }

    @Test
    fun `logIn fallido test`(){
        val exceptionName = assertThrows(UsuarioException::class.java){
            usuarioService.logIn("aaa","123")
        }
        val exceptionPass = assertThrows(UsuarioException::class.java){
            usuarioService.logIn("userMock","5555")
        }
        assertEquals("usuario no encontrado", exceptionName.message)
        assertEquals("usuario no encontrado", exceptionPass.message)
    }

    @Test
    fun `eliminar fallido test`(){
        val exception = assertThrows(UsuarioException::class.java){
            usuarioService.eliminarUsuario("1")
        }
        assertEquals("No existe usuario con id 1", exception.message)

    }


    @AfterEach
    fun `borrar instancia`(){
        usuarioService.eliminarUsuario(usuarioCreado.id!!)
    }

}