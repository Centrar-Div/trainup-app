package ar.com.unq.eis.trainup

import EjercicioService
import ar.com.unq.eis.trainup.model.Ejercicio
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.services.RutinaService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TrainupApplicationTests {

	@Autowired private lateinit var rutinaService : RutinaService
	@Autowired private lateinit var ejercicioService : EjercicioService

	private lateinit var  ejercicioPiernas : Ejercicio
	private lateinit var  ejercicioBanco : Ejercicio
	private lateinit var  rutina : Rutina

	@Test
	fun contextLoads() {

		// Ejercicio
		val ejercicioPiernas = Ejercicio(
			nombre = "Sentadilla",
			descripcion = "Sentadilla profunda con barra",
			repeticiones = 12,
			peso = 80.0,
			musculo = "Piernas"
		)

		val ejercicioBarra = Ejercicio(
			nombre = "Press banca",
			descripcion = "Press en banca plana con barra",
			repeticiones = 10,
			peso = 70.0,
			musculo = "Pectorales"
		)

		ejercicioService.crearEjercicio(ejercicioPiernas)
		ejercicioService.crearEjercicio(ejercicioBarra)

		// Rutina
		val rutina = Rutina(nombre = "Rutina de fuerza")
		rutina.agregarEjercicio(ejercicioPiernas)
		rutina.agregarEjercicio(ejercicioBarra)

		rutinaService.crearRutina(rutina)

	}

}
