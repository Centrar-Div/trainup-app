package ar.com.unq.eis.trainup.modeloTest

import ar.com.unq.eis.trainup.model.Ejercicio
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class EjercicioTest {

    private lateinit var ejercicio: Ejercicio

    @BeforeEach
    fun setUp() {
        ejercicio = Ejercicio(
            id = "1",
            nombre = "Press de Banca",
            descripcion = "Ejercicio para el pecho",
            repeticiones = 10,
            peso = 60.0,
            musculo = "Pecho"
        )
    }

    @Test
    fun testEjercicioEsCorrecto() {
        assertEquals("1", ejercicio.id)
        assertEquals("Press de Banca", ejercicio.nombre)
        assertEquals("Ejercicio para el pecho", ejercicio.descripcion)
        assertEquals(10, ejercicio.repeticiones)
        assertEquals(60.0, ejercicio.peso)
        assertEquals("Pecho", ejercicio.musculo)
    }

    @Test
    fun testEjercicioSetNombre() {
        ejercicio.nombre = "Sentadilla"
        assertEquals("Sentadilla", ejercicio.nombre)
    }

    @Test
    fun testEjercicioSetRepeticiones() {
        ejercicio.repeticiones = 12
        assertEquals(12, ejercicio.repeticiones)
    }

    @Test
    fun testToString() {
        val expected = "Ejercicio(id='1', nombre='Press de Banca', descripcion='Ejercicio para el pecho', repeticiones=10, peso=60.0, musculo='Pecho')"
        assertEquals(expected, ejercicio.toString())
    }

    // TEST VALIDACIONES

    @Test
    fun testNombreNoPuedeSerVacio() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "1",
                nombre = "",
                descripcion = "Ejercicio para el pecho",
                repeticiones = 10,
                peso = 60.0,
                musculo = "Pecho"
            )
        }
        assertEquals("El nombre no puede estar vacío", exception.message)
    }

    @Test
    fun testDescripcionNoPuedeSerVacia() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "2",
                nombre = "Sentadilla",
                descripcion = "",
                repeticiones = 10,
                peso = 100.0,
                musculo = "Piernas"
            )
        }
        assertEquals("La descripción no puede estar vacía", exception.message)
    }

    @Test
    fun testRepeticionesDebeSerMayorQueCero() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "3",
                nombre = "Peso muerto",
                descripcion = "Ejercicio para la espalda",
                repeticiones = 0,
                peso = 80.0,
                musculo = "Espalda"
            )
        }
        assertEquals("Las repeticiones deben ser mayores a 0", exception.message)
    }

    @Test
    fun testPesoNoPuedeSerNegativo() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "4",
                nombre = "Press de banca",
                descripcion = "Ejercicio para el pecho",
                repeticiones = 10,
                peso = -10.0,
                musculo = "Pecho"
            )
        }
        assertEquals("El peso no puede ser negativo", exception.message)
    }

    @Test
    fun testMusculoNoPuedeSerVacio() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "5",
                nombre = "Dominadas",
                descripcion = "Ejercicio para la espalda",
                repeticiones = 6,
                peso = 0.0,
                musculo = ""
            )
        }
        assertEquals("El nombre del músculo no puede estar vacío", exception.message)
    }

}