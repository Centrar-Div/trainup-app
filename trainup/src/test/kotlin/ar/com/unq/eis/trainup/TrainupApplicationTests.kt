package ar.com.unq.eis.trainup

import EjercicioService
import ar.com.unq.eis.trainup.model.Ejercicio
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.model.Usuario
import ar.com.unq.eis.trainup.services.RutinaService
import ar.com.unq.eis.trainup.services.UsuarioService
import ar.com.unq.eis.trainup.services.impl.UsuarioServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class TrainupApplicationTests {

    @Autowired
    private lateinit var rutinaService: RutinaService

    @Autowired
    private lateinit var ejercicioService: EjercicioService

    @Autowired
    private lateinit var usuarioService: UsuarioService

    private lateinit var ejercicioPiernas: Ejercicio
    private lateinit var ejercicioBanco: Ejercicio
    private lateinit var rutina: Rutina
    private lateinit var usuario0: Usuario

    @Test
    fun contextLoads() {

        // Rutina 1 - Fuerza
        val ejerciciosFuerza = listOf(
            Ejercicio(
                nombre = "Sentadilla",
                descripcion = "Sentadilla con barra",
                repeticiones = 12,
                peso = 80.0,
                musculo = "Piernas"
            ),
            Ejercicio(
                nombre = "Press banca",
                descripcion = "Press en banca plana",
                repeticiones = 10,
                peso = 70.0,
                musculo = "Pectorales"
            ),
            Ejercicio(
                nombre = "Peso muerto",
                descripcion = "Peso muerto convencional",
                repeticiones = 8,
                peso = 100.0,
                musculo = "Espalda"
            ),
            Ejercicio(
                nombre = "Remo con barra",
                descripcion = "Remo con barra",
                repeticiones = 12,
                peso = 60.0,
                musculo = "Espalda"
            ),
            Ejercicio(
                nombre = "Press militar",
                descripcion = "Press militar con barra",
                repeticiones = 8,
                peso = 40.0,
                musculo = "Hombros"
            ),
            Ejercicio(
                nombre = "Curl de bíceps",
                descripcion = "Curl con barra",
                repeticiones = 10,
                peso = 30.0,
                musculo = "Bíceps"
            ),
            Ejercicio(
                nombre = "Fondos",
                descripcion = "Fondos en paralelas",
                repeticiones = 15,
                peso = 0.0,
                musculo = "Tríceps"
            )
        )
        ejerciciosFuerza.forEach { ejercicioService.crearEjercicio(it) }

        val rutinaFuerza = Rutina(
            nombre = "Rutina de fuerza",
            descripcion = "Entrenamiento enfocado en fuerza máxima",
            categoria = "Fuerza",
            ejercicios = ejerciciosFuerza
        )
       val rutinaFuerzaPersistida = rutinaService.crearRutina(rutinaFuerza)

        // Rutina 2 - Hipertrofia
        val ejerciciosHipertrofia = listOf(
            Ejercicio(
                nombre = "Prensa de pierna",
                descripcion = "Prensa de pierna en máquina",
                repeticiones = 15,
                peso = 100.0,
                musculo = "Piernas"
            ),
            Ejercicio(
                nombre = "Aperturas",
                descripcion = "Aperturas con mancuernas",
                repeticiones = 12,
                peso = 12.0,
                musculo = "Pectorales"
            ),
            Ejercicio(
                nombre = "Dominadas",
                descripcion = "Dominadas en barra",
                repeticiones = 10,
                peso = 0.0,
                musculo = "Espalda"
            ),
            Ejercicio(
                nombre = "Remo en polea",
                descripcion = "Remo en polea baja",
                repeticiones = 12,
                peso = 50.0,
                musculo = "Espalda"
            ),
            Ejercicio(
                nombre = "Elevaciones laterales",
                descripcion = "Elevaciones laterales con mancuernas",
                repeticiones = 15,
                peso = 8.0,
                musculo = "Hombros"
            ),
            Ejercicio(
                nombre = "Martillo",
                descripcion = "Curl de bíceps martillo",
                repeticiones = 12,
                peso = 14.0,
                musculo = "Bíceps"
            ),
            Ejercicio(
                nombre = "Extensión de tríceps",
                descripcion = "Extensión de tríceps en polea",
                repeticiones = 12,
                peso = 30.0,
                musculo = "Tríceps"
            )
        )
        ejerciciosHipertrofia.forEach { ejercicioService.crearEjercicio(it) }
        val rutinaHipertrofia = Rutina(
            nombre = "Rutina de hipertrofia",
            descripcion = "Entrenamiento enfocado en el crecimiento muscular",
            categoria = "Hipertrofia",
            ejercicios = ejerciciosHipertrofia
        )
        val rutinaHipertrofiaPersistida = rutinaService.crearRutina(rutinaHipertrofia)

        // Rutina 3 - Resistencia
        val ejerciciosResistencia = listOf(
            Ejercicio(
                nombre = "Burpees",
                descripcion = "Ejercicio de burpees",
                repeticiones = 20,
                peso = 0.0,
                musculo = "Full body"
            ),
            Ejercicio(
                nombre = "Mountain climbers",
                descripcion = "Mountain climbers",
                repeticiones = 30,
                peso = 0.0,
                musculo = "Core"
            ),
            Ejercicio(
                nombre = "Plancha",
                descripcion = "Plancha isométrica",
                repeticiones = 60,
                peso = 0.0,
                musculo = "Core"
            ),
            Ejercicio(
                nombre = "Zancadas",
                descripcion = "Zancadas con mancuernas",
                repeticiones = 20,
                peso = 10.0,
                musculo = "Piernas"
            ),
            Ejercicio(
                nombre = "Jumping jacks",
                descripcion = "Jumping jacks",
                repeticiones = 50,
                peso = 0.0,
                musculo = "Full body"
            ),
            Ejercicio(
                nombre = "Sprints",
                descripcion = "Sprints de 100 metros",
                repeticiones = 5,
                peso = 0.0,
                musculo = "Piernas"
            ),
            Ejercicio(nombre = "Skipping", descripcion = "Skipping", repeticiones = 50, peso = 0.0, musculo = "Piernas")
        )
        ejerciciosResistencia.forEach { ejercicioService.crearEjercicio(it) }
        val rutinaResistencia = Rutina(
            nombre = "Rutina de resistencia",
            descripcion = "Entrenamiento para mejorar la resistencia cardiovascular y muscular",
            categoria = "Resistencia",
            ejercicios = ejerciciosResistencia
        )
        val rutinaResistenciaPersisitida = rutinaService.crearRutina(rutinaResistencia)

        // Rutina 4 - Funcional
        val ejerciciosFuncional = listOf(
            Ejercicio(
                nombre = "Kettlebell swing",
                descripcion = "Swing con kettlebell",
                repeticiones = 15,
                peso = 20.0,
                musculo = "Full body"
            ),
            Ejercicio(
                nombre = "Saltos a la caja",
                descripcion = "Saltos a la caja",
                repeticiones = 12,
                peso = 0.0,
                musculo = "Piernas"
            ),
            Ejercicio(
                nombre = "Clean and jerk",
                descripcion = "Clean and jerk con barra",
                repeticiones = 8,
                peso = 60.0,
                musculo = "Full body"
            ),
            Ejercicio(
                nombre = "Snatch",
                descripcion = "Snatch con barra",
                repeticiones = 8,
                peso = 50.0,
                musculo = "Full body"
            ),
            Ejercicio(
                nombre = "Wall balls",
                descripcion = "Wall balls con pelota medicinal",
                repeticiones = 20,
                peso = 10.0,
                musculo = "Full body"
            ),
            Ejercicio(
                nombre = "Farmer's walk",
                descripcion = "Farmer's walk con mancuernas",
                repeticiones = 50,
                peso = 25.0,
                musculo = "Full body"
            ),
            Ejercicio(
                nombre = "Escaladores",
                descripcion = "Mountain climbers",
                repeticiones = 30,
                peso = 0.0,
                musculo = "Core"
            )
        )
        ejerciciosFuncional.forEach { ejercicioService.crearEjercicio(it) }
        val rutinaFuncional = Rutina(
            nombre = "Rutina funcional",
            descripcion = "Entrenamiento funcional para todo el cuerpo",
            categoria = "Funcional",
            ejercicios = ejerciciosFuncional
        )
        var rutinaFuncionalPerisistida = rutinaService.crearRutina(rutinaFuncional)

        usuario0 = Usuario(
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

        //RUTINA 5
        val ejerciciosPotencia = listOf(
            Ejercicio(
                nombre = "Peso muerto",
                descripcion = "Peso muerto con barra",
                repeticiones = 8,
                peso = 80.0,
                musculo = "Espalda baja"
            ),
            Ejercicio(
                nombre = "Sentadilla con barra",
                descripcion = "Sentadilla profunda con barra",
                repeticiones = 10,
                peso = 70.0,
                musculo = "Piernas"
            ),
            Ejercicio(
                nombre = "Press banca",
                descripcion = "Press de banca con barra",
                repeticiones = 8,
                peso = 60.0,
                musculo = "Pectorales"
            ),
            Ejercicio(
                nombre = "Remo con barra",
                descripcion = "Remo con barra para espalda",
                repeticiones = 10,
                peso = 50.0,
                musculo = "Espalda alta"
            ),
            Ejercicio(
                nombre = "Press militar",
                descripcion = "Press militar de pie",
                repeticiones = 8,
                peso = 40.0,
                musculo = "Hombros"
            )
        )
        ejerciciosFuerza.forEach { ejercicioService.crearEjercicio(it) }
        val rutinaPotencia = Rutina(
            nombre = "Rutina de Fuerza y Potencia",
            descripcion = "Entrenamiento para mejorar fuerza máxima y potencia",
            categoria = "Fuerza",
            ejercicios = ejerciciosFuerza
        )


        //RUTINA 6
        val ejerciciosCore = listOf(
            Ejercicio(
                nombre = "Burpees",
                descripcion = "Burpees con salto",
                repeticiones = 20,
                peso = 0.0,
                musculo = "Full body"
            ),
            Ejercicio(
                nombre = "Plancha",
                descripcion = "Plancha estática",
                repeticiones = 1,
                peso = 0.0,
                musculo = "Core"
            ),
            Ejercicio(
                nombre = "Zancadas caminando",
                descripcion = "Zancadas con mancuernas",
                repeticiones = 20,
                peso = 15.0,
                musculo = "Piernas"
            ),
            Ejercicio(
                nombre = "Russian twists",
                descripcion = "Giros rusos con balón medicinal",
                repeticiones = 40,
                peso = 8.0,
                musculo = "Core"
            ),
            Ejercicio(
                nombre = "Elevación de piernas",
                descripcion = "Elevación de piernas colgando",
                repeticiones = 15,
                peso = 0.0,
                musculo = "Core"
            )
        )
        ejerciciosResistencia.forEach { ejercicioService.crearEjercicio(it) }
        val rutinaCore = Rutina(
            nombre = "Rutina de Resistencia y Core",
            descripcion = "Entrenamiento centrado en resistencia muscular y core",
            categoria = "Resistencia",
            ejercicios = ejerciciosResistencia
        )

        //RUTINA 7
        val ejerciciosHIIT = listOf(
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
            ),
            Ejercicio(
                nombre = "Clean con mancuernas",
                descripcion = "Clean explosivo con mancuernas",
                repeticiones = 12,
                peso = 20.0,
                musculo = "Full body"
            ),
            Ejercicio(
                nombre = "Push-ups",
                descripcion = "Flexiones explosivas",
                repeticiones = 20,
                peso = 0.0,
                musculo = "Pectorales"
            ),
            Ejercicio(
                nombre = "Mountain climbers",
                descripcion = "Escaladores a alta velocidad",
                repeticiones = 40,
                peso = 0.0,
                musculo = "Core"
            )
        )
        ejerciciosHIIT.forEach { ejercicioService.crearEjercicio(it) }
        val rutinaHIIT = Rutina(
            nombre = "Rutina HIIT",
            descripcion = "Entrenamiento de intervalos de alta intensidad",
            categoria = "HIIT",
            ejercicios = ejerciciosHIIT
        )



        usuario0.rutinasSeguidas.add(rutinaFuerzaPersistida)
        usuario0.rutinasSeguidas.add(rutinaFuncionalPerisistida)
        usuario0.rutinasSeguidas.add(rutinaHipertrofiaPersistida)


        usuarioService.crearUsuario(usuario0)
    }


}
