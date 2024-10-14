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
            musculo = "Pecho",
            series = 3,
            descansoSegundos = 60,
            equipo = "Banco y barra",
            instrucciones = "Asegurarse de tener un buen agarre en la barra"
        )
    }

    @Test
    fun `test que el ejercicio tiene los valores correctos`() {
        assertEquals("1", ejercicio.id)
        assertEquals("Press de Banca", ejercicio.nombre)
        assertEquals("Ejercicio para el pecho", ejercicio.descripcion)
        assertEquals(10, ejercicio.repeticiones)
        assertEquals(60.0, ejercicio.peso)
        assertEquals("Pecho", ejercicio.musculo)
        assertEquals(3, ejercicio.series)
        assertEquals(60, ejercicio.descansoSegundos)
        assertEquals("Banco y barra", ejercicio.equipo)
        assertEquals("Asegurarse de tener un buen agarre en la barra", ejercicio.instrucciones)
    }

    @Test
    fun `test actualizacion de nombre`() {
        ejercicio.nombre = "Sentadilla"
        assertEquals("Sentadilla", ejercicio.nombre)
    }

    @Test
    fun `test actualizacion de repeticiones`() {
        ejercicio.repeticiones = 12
        assertEquals(12, ejercicio.repeticiones)
    }

    @Test
    fun `test toString funciona correctamente`() {
        val expected = "Ejercicio(id='1', nombre='Press de Banca', descripcion='Ejercicio para el pecho', repeticiones=10, peso=60.0, musculo='Pecho', series=3, descansoSegundos=60, equipo='Banco y barra', instrucciones='Asegurarse de tener un buen agarre en la barra', completado=false)"
        assertEquals(expected, ejercicio.toString())
    }

    // TEST VALIDACIONES

    @Test
    fun `test nombre no puede ser vacio`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "1",
                nombre = "",
                descripcion = "Ejercicio para el pecho",
                repeticiones = 10,
                peso = 60.0,
                musculo = "Pecho",
                series = 3,
                descansoSegundos = 60,
                equipo = "Banco y barra",
                instrucciones = "Asegurarse de tener un buen agarre en la barra"
            )
        }
        assertEquals("El nombre no puede estar vacío", exception.message)
    }

    @Test
    fun `test descripcion no puede ser vacia`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "2",
                nombre = "Sentadilla",
                descripcion = "",
                repeticiones = 10,
                peso = 100.0,
                musculo = "Piernas",
                series = 4,
                descansoSegundos = 90,
                equipo = "Rack",
                instrucciones = "Asegurarse de bajar bien"
            )
        }
        assertEquals("La descripción no puede estar vacía", exception.message)
    }

    @Test
    fun `test repeticiones debe ser mayor que cero`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "3",
                nombre = "Peso muerto",
                descripcion = "Ejercicio para la espalda",
                repeticiones = 0,
                peso = 80.0,
                musculo = "Espalda",
                series = 4,
                descansoSegundos = 60,
                equipo = "Barra",
                instrucciones = "Mantener la espalda recta"
            )
        }
        assertEquals("Las repeticiones deben ser mayores a 0", exception.message)
    }

    @Test
    fun `test peso no puede ser negativo`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "4",
                nombre = "Press de banca",
                descripcion = "Ejercicio para el pecho",
                repeticiones = 10,
                peso = -10.0,
                musculo = "Pecho",
                series = 4,
                descansoSegundos = 60,
                equipo = "Banco",
                instrucciones = "Agarre seguro"
            )
        }
        assertEquals("El peso no puede ser negativo", exception.message)
    }

    @Test
    fun `test musculo no puede ser vacio`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "5",
                nombre = "Dominadas",
                descripcion = "Ejercicio para la espalda",
                repeticiones = 6,
                peso = 0.0,
                musculo = "",
                series = 4,
                descansoSegundos = 60,
                equipo = "Barra",
                instrucciones = "Subir bien"
            )
        }
        assertEquals("El nombre del músculo no puede estar vacío", exception.message)
    }

    @Test
    fun `test series deben ser mayores que cero`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "6",
                nombre = "Press militar",
                descripcion = "Ejercicio para hombros",
                repeticiones = 8,
                peso = 50.0,
                musculo = "Hombros",
                series = 0,
                descansoSegundos = 60,
                equipo = "Barra",
                instrucciones = "Agarre firme"
            )
        }
        assertEquals("Las series deben ser mayores a 0", exception.message)
    }

    @Test
    fun `test descanso no puede ser negativo`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Ejercicio(
                id = "7",
                nombre = "Curl de bíceps",
                descripcion = "Ejercicio para bíceps",
                repeticiones = 10,
                peso = 30.0,
                musculo = "Bíceps",
                series = 4,
                descansoSegundos = -30,
                equipo = "Mancuernas",
                instrucciones = "Controlar la bajada"
            )
        }
        assertEquals("El tiempo de descanso no puede ser negativo", exception.message)
    }
}
