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

		// Rutina 1 - Fuerza
		val rutinaFuerza = Rutina(nombre = "Rutina de fuerza")
		val ejerciciosFuerza = listOf(
			Ejercicio(nombre = "Sentadilla", descripcion = "Sentadilla con barra", repeticiones = 12, peso = 80.0, musculo = "Piernas"),
			Ejercicio(nombre = "Press banca", descripcion = "Press en banca plana", repeticiones = 10, peso = 70.0, musculo = "Pectorales"),
			Ejercicio(nombre = "Peso muerto", descripcion = "Peso muerto convencional", repeticiones = 8, peso = 100.0, musculo = "Espalda"),
			Ejercicio(nombre = "Remo con barra", descripcion = "Remo con barra", repeticiones = 12, peso = 60.0, musculo = "Espalda"),
			Ejercicio(nombre = "Press militar", descripcion = "Press militar con barra", repeticiones = 8, peso = 40.0, musculo = "Hombros"),
			Ejercicio(nombre = "Curl de bíceps", descripcion = "Curl con barra", repeticiones = 10, peso = 30.0, musculo = "Bíceps"),
			Ejercicio(nombre = "Fondos", descripcion = "Fondos en paralelas", repeticiones = 15, peso = 0.0, musculo = "Tríceps")
		)
		ejerciciosFuerza.forEach { ejercicioService.crearEjercicio(it) }
		ejerciciosFuerza.forEach { rutinaFuerza.agregarEjercicio(it) }
		rutinaService.crearRutina(rutinaFuerza)

		// Rutina 2 - Hipertrofia
		val rutinaHipertrofia = Rutina(nombre = "Rutina de hipertrofia")
		val ejerciciosHipertrofia = listOf(
			Ejercicio(nombre = "Prensa de pierna", descripcion = "Prensa de pierna en máquina", repeticiones = 15, peso = 100.0, musculo = "Piernas"),
			Ejercicio(nombre = "Aperturas", descripcion = "Aperturas con mancuernas", repeticiones = 12, peso = 12.0, musculo = "Pectorales"),
			Ejercicio(nombre = "Dominadas", descripcion = "Dominadas en barra", repeticiones = 10, peso = 0.0, musculo = "Espalda"),
			Ejercicio(nombre = "Remo en polea", descripcion = "Remo en polea baja", repeticiones = 12, peso = 50.0, musculo = "Espalda"),
			Ejercicio(nombre = "Elevaciones laterales", descripcion = "Elevaciones laterales con mancuernas", repeticiones = 15, peso = 8.0, musculo = "Hombros"),
			Ejercicio(nombre = "Martillo", descripcion = "Curl de bíceps martillo", repeticiones = 12, peso = 14.0, musculo = "Bíceps"),
			Ejercicio(nombre = "Extensión de tríceps", descripcion = "Extensión de tríceps en polea", repeticiones = 12, peso = 30.0, musculo = "Tríceps")
		)
		ejerciciosHipertrofia.forEach { ejercicioService.crearEjercicio(it) }
		ejerciciosHipertrofia.forEach { rutinaHipertrofia.agregarEjercicio(it) }
		rutinaService.crearRutina(rutinaHipertrofia)

		// Rutina 3 - Resistencia
		val rutinaResistencia = Rutina(nombre = "Rutina de resistencia")
		val ejerciciosResistencia = listOf(
			Ejercicio(nombre = "Burpees", descripcion = "Ejercicio de burpees", repeticiones = 20, peso = 0.0, musculo = "Full body"),
			Ejercicio(nombre = "Mountain climbers", descripcion = "Mountain climbers", repeticiones = 30, peso = 0.0, musculo = "Core"),
			Ejercicio(nombre = "Plancha", descripcion = "Plancha isométrica", repeticiones = 60, peso = 0.0, musculo = "Core"),
			Ejercicio(nombre = "Zancadas", descripcion = "Zancadas con mancuernas", repeticiones = 20, peso = 10.0, musculo = "Piernas"),
			Ejercicio(nombre = "Jumping jacks", descripcion = "Jumping jacks", repeticiones = 50, peso = 0.0, musculo = "Full body"),
			Ejercicio(nombre = "Sprints", descripcion = "Sprints de 100 metros", repeticiones = 5, peso = 0.0, musculo = "Piernas"),
			Ejercicio(nombre = "Skipping", descripcion = "Skipping", repeticiones = 50, peso = 0.0, musculo = "Piernas")
		)
		ejerciciosResistencia.forEach { ejercicioService.crearEjercicio(it) }
		ejerciciosResistencia.forEach { rutinaResistencia.agregarEjercicio(it) }
		rutinaService.crearRutina(rutinaResistencia)

		// Rutina 4 - Funcional
		val rutinaFuncional = Rutina(nombre = "Rutina funcional")
		val ejerciciosFuncional = listOf(
			Ejercicio(nombre = "Kettlebell swing", descripcion = "Swing con kettlebell", repeticiones = 15, peso = 20.0, musculo = "Full body"),
			Ejercicio(nombre = "Saltos a la caja", descripcion = "Saltos a la caja", repeticiones = 12, peso = 0.0, musculo = "Piernas"),
			Ejercicio(nombre = "Clean and jerk", descripcion = "Clean and jerk con barra", repeticiones = 8, peso = 60.0, musculo = "Full body"),
			Ejercicio(nombre = "Snatch", descripcion = "Snatch con barra", repeticiones = 8, peso = 50.0, musculo = "Full body"),
			Ejercicio(nombre = "Wall balls", descripcion = "Wall balls con pelota medicinal", repeticiones = 20, peso = 10.0, musculo = "Full body"),
			Ejercicio(nombre = "Farmer's walk", descripcion = "Farmer's walk con mancuernas", repeticiones = 50, peso = 25.0, musculo = "Full body"),
			Ejercicio(nombre = "Escaladores", descripcion = "Mountain climbers", repeticiones = 30, peso = 0.0, musculo = "Core")
		)
		ejerciciosFuncional.forEach { ejercicioService.crearEjercicio(it) }
		ejerciciosFuncional.forEach { rutinaFuncional.agregarEjercicio(it) }
		rutinaService.crearRutina(rutinaFuncional)

	}

}
