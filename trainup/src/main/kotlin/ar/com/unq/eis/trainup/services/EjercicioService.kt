import ar.com.unq.eis.trainup.model.Ejercicio

interface EjercicioService {

    fun crearEjercicio(ejercicio: Ejercicio): Ejercicio

    fun obtenerEjercicios(): List<Ejercicio>

    fun obtenerEjercicioPorId(id: String): Ejercicio

    fun actualizarEjercicio(id: String, ejercicioActualizado: Ejercicio): Ejercicio

    fun eliminarEjercicio(id: String)
}