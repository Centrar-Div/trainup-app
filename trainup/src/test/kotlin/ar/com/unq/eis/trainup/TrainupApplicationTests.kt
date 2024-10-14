package ar.com.unq.eis.trainup

import EjercicioService
import ar.com.unq.eis.trainup.model.Ejercicio
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.model.Usuario
import ar.com.unq.eis.trainup.services.RutinaService
import ar.com.unq.eis.trainup.services.UsuarioService
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

    @Test
    fun contextLoads() {
        val rutinas = mutableListOf<Rutina>()

        val todosEjercicios = listOf(
            // Fuerza
            Ejercicio(
                id = null,
                nombre = "Sentadilla con barra",
                descripcion = "Sentadilla con barra en la espalda",
                repeticiones = 5,
                peso = 100.0,
                musculo = "Piernas",
                series = 4,
                descansoSegundos = 90,
                equipo = "Barra y discos",
                instrucciones = "Mantén la espalda recta durante todo el movimiento.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Peso muerto",
                descripcion = "Peso muerto convencional",
                repeticiones = 5,
                peso = 120.0,
                musculo = "Espalda baja",
                series = 4,
                descansoSegundos = 120,
                equipo = "Barra y discos",
                instrucciones = "Caderas hacia atrás, peso en los talones.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Press banca inclinado",
                descripcion = "Press en banca inclinada con barra",
                repeticiones = 10,
                peso = 70.0,
                musculo = "Pectorales",
                series = 3,
                descansoSegundos = 60,
                equipo = "Barra y discos",
                instrucciones = "Controla la bajada, empuja explosivamente.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Aperturas con mancuernas",
                descripcion = "Aperturas en banco plano",
                repeticiones = 12,
                peso = 15.0,
                musculo = "Pectorales",
                series = 3,
                descansoSegundos = 60,
                equipo = "Mancuernas",
                instrucciones = "Baja con control, no sobrepases la altura de los hombros.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Press militar",
                descripcion = "Press militar con barra",
                repeticiones = 8,
                peso = 50.0,
                musculo = "Hombros",
                series = 4,
                descansoSegundos = 90,
                equipo = "Barra y discos",
                instrucciones = "Mantén los codos hacia adelante durante el levantamiento.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Remo con barra",
                descripcion = "Remo con barra para espalda",
                repeticiones = 10,
                peso = 80.0,
                musculo = "Espalda alta",
                series = 3,
                descansoSegundos = 90,
                equipo = "Barra y discos",
                instrucciones = "Controla la subida y bajada de la barra.",
                completado = false
            ),

            // Hipertrofia
            Ejercicio(
                id = null,
                nombre = "Curl de bíceps con barra",
                descripcion = "Curl con barra para bíceps",
                repeticiones = 12,
                peso = 30.0,
                musculo = "Bíceps",
                series = 4,
                descansoSegundos = 60,
                equipo = "Barra",
                instrucciones = "Sube y baja el peso de manera controlada.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Fondos en paralelas",
                descripcion = "Fondos en paralelas para tríceps",
                repeticiones = 12,
                peso = 0.0,
                musculo = "Tríceps",
                series = 4,
                descansoSegundos = 60,
                equipo = "Paralelas",
                instrucciones = "Mantén el torso inclinado hacia adelante para mayor énfasis en los tríceps.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Extensiones de tríceps en polea",
                descripcion = "Extensiones de tríceps con cuerda en polea alta",
                repeticiones = 12,
                peso = 40.0,
                musculo = "Tríceps",
                series = 3,
                descansoSegundos = 60,
                equipo = "Polea",
                instrucciones = "Evita mover los codos durante el ejercicio.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Press Arnold",
                descripcion = "Press Arnold con mancuernas para hombros",
                repeticiones = 10,
                peso = 25.0,
                musculo = "Hombros",
                series = 4,
                descansoSegundos = 60,
                equipo = "Mancuernas",
                instrucciones = "Gira las muñecas al levantar el peso.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Elevaciones laterales con mancuernas",
                descripcion = "Elevaciones laterales para los hombros",
                repeticiones = 15,
                peso = 10.0,
                musculo = "Hombros",
                series = 4,
                descansoSegundos = 60,
                equipo = "Mancuernas",
                instrucciones = "Mantén una ligera flexión en los codos.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Crunch abdominal",
                descripcion = "Ejercicio básico de abdominales",
                repeticiones = 20,
                peso = 0.0,
                musculo = "Abdominales",
                series = 3,
                descansoSegundos = 30,
                equipo = "Ninguno",
                instrucciones = "No tires del cuello mientras te elevas.",
                completado = false
            ),

            // Cardio y resistencia
            Ejercicio(
                id = null,
                nombre = "Burpees",
                descripcion = "Ejercicio de cuerpo completo con salto y flexión",
                repeticiones = 20,
                peso = 0.0,
                musculo = "Full Body",
                series = 4,
                descansoSegundos = 60,
                equipo = "Ninguno",
                instrucciones = "Realiza el salto con explosividad.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Mountain Climbers",
                descripcion = "Escaladores en el suelo para core",
                repeticiones = 30,
                peso = 0.0,
                musculo = "Core",
                series = 4,
                descansoSegundos = 30,
                equipo = "Ninguno",
                instrucciones = "Mantén la espalda recta y eleva las rodillas lo más rápido posible.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Jumping Jacks",
                descripcion = "Saltos de tijera",
                repeticiones = 50,
                peso = 0.0,
                musculo = "Full Body",
                series = 4,
                descansoSegundos = 30,
                equipo = "Ninguno",
                instrucciones = "Realiza los saltos a un ritmo constante.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Salto a la cuerda",
                descripcion = "Saltos con cuerda a alta intensidad",
                repeticiones = 100,
                peso = 0.0,
                musculo = "Piernas",
                series = 4,
                descansoSegundos = 60,
                equipo = "Cuerda",
                instrucciones = "Mantén un ritmo constante y controla la respiración.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Zancadas alternas",
                descripcion = "Zancadas con alternancia de piernas",
                repeticiones = 20,
                peso = 0.0,
                musculo = "Piernas",
                series = 4,
                descansoSegundos = 60,
                equipo = "Ninguno",
                instrucciones = "Baja hasta que ambas rodillas formen ángulos de 90 grados.",
                completado = false
            ),

            // Funcional
            Ejercicio(
                id = null,
                nombre = "Kettlebell swing",
                descripcion = "Balanceo de kettlebell para full body",
                repeticiones = 20,
                peso = 16.0,
                musculo = "Full Body",
                series = 4,
                descansoSegundos = 60,
                equipo = "Kettlebell",
                instrucciones = "Mantén el core apretado durante todo el movimiento.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Clean and Press",
                descripcion = "Clean and Press con mancuernas",
                repeticiones = 10,
                peso = 20.0,
                musculo = "Full Body",
                series = 4,
                descansoSegundos = 60,
                equipo = "Mancuernas",
                instrucciones = "Controla el peso al levantar y presionar hacia arriba.",
                completado = false
            ),
            Ejercicio(
                id = null,
                nombre = "Farmer's Walk",
                descripcion = "Caminata con mancuernas pesadas",
                repeticiones = 60,
                peso = 30.0,
                musculo = "Full Body",
                series = 3,
                descansoSegundos = 60,
                equipo = "Mancuernas",
                instrucciones = "Mantén una postura erguida mientras caminas.",
                completado = false
            ),
        )


        todosEjercicios.forEach { ejercicioService.crearEjercicio(it) }

        // Genera 40 rutinas, cada una con entre 4 y 7 ejercicios aleatorios de la lista
        for (i in 1..40) {
            val categoria = when (i % 5) {
                1 -> "Fuerza"
                2 -> "Hipertrofia"
                3 -> "Resistencia"
                4 -> "Cardio"
                else -> "Funcional"
            }

            val ejercicios = todosEjercicios.shuffled().take((4..7).random()).toMutableList()

            val rutina = Rutina(
                id = null,
                nombre = "Rutina $categoria $i",
                descripcion = "Entrenamiento enfocado en $categoria",
                categoria = categoria,
                dificultad = if (i % 2 == 0) "Intermedio" else "Avanzado",
                duracionMinutos = (30..60).random(),
                objetivo = when (categoria) {
                    "Fuerza" -> "Ganar fuerza"
                    "Hipertrofia" -> "Aumentar masa muscular"
                    "Resistencia" -> "Mejorar resistencia"
                    "Cardio" -> "Quemar grasa"
                    else -> "Mejorar la condición física general"
                },
                frecuenciaSemanal = (3..5).random(),
                ejercicios = ejercicios
            )

            val rutinaPersistida = rutinaService.crearRutina(rutina)
            rutinas.add(rutinaPersistida)
        }

        val usuarioNormal = Usuario(
            username = "usuarioNormal",
            password = "password123",
            nombre = "Usuario Normal",
            edad = 25,
            fecNacimiento = LocalDate.of(1999, 3, 15),
            telefono = "123456789",
            genero = "masculino",
            altura = "175",
            peso = "70",
            objetivo = "ganar masa muscular",
            esAdmin = false
        )

        val usuarioAdmin = Usuario(
            username = "adminUser",
            password = "adminPass123",
            nombre = "Admin User",
            edad = 30,
            fecNacimiento = LocalDate.of(1993, 1, 5),
            telefono = "987654321",
            genero = "femenino",
            altura = "165",
            peso = "60",
            objetivo = "mantener la forma",
            esAdmin = true
        )

        usuarioNormal.rutinasSeguidas.addAll(rutinas.shuffled().take(3))
        usuarioAdmin.rutinasSeguidas.addAll(rutinas.shuffled().take(3))

        usuarioService.crearUsuario(usuarioNormal)
        usuarioService.crearUsuario(usuarioAdmin)

        usuarioNormal.rutinasSeguidas.addAll(rutinas.shuffled().take(2))
        usuarioService.actualizarUsuario(usuarioNormal)
    }
}
