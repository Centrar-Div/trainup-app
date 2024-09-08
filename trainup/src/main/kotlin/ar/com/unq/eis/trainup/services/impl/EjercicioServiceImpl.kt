package ar.com.unq.eis.trainup.services.impl

import EjercicioService
import ar.com.unq.eis.trainup.dao.EjercicioDAO
import ar.com.unq.eis.trainup.model.Ejercicio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EjercicioServiceImpl(
    @Autowired private val ejercicioDAO: EjercicioDAO
) : EjercicioService {

    override fun crearEjercicio(ejercicio: Ejercicio): Ejercicio {
        return try {
            ejercicioDAO.save(ejercicio)
        } catch (e: Exception) {
            throw RuntimeException("Error al crear el ejercicio: ${e.message}")
        }
    }

    override fun obtenerEjercicios(): List<Ejercicio> {
        return try {
            ejercicioDAO.findAll()
        } catch (e: Exception) {
            throw RuntimeException("Error al obtener la lista de ejercicios: ${e.message}")
        }
    }

    override fun obtenerEjercicioPorId(id: String): Ejercicio {
        return try {
            val ejercicio = ejercicioDAO.findById(id)
            if (ejercicio.isPresent) {
                ejercicio.get()
            } else {
                throw NoSuchElementException("No se encontró el ejercicio con id: $id")
            }
        } catch (e: NoSuchElementException) {
            throw e
        } catch (e: Exception) {
            throw RuntimeException("Error al buscar el ejercicio por id: ${e.message}")
        }
    }

    override fun actualizarEjercicio(id: String, ejercicioActualizado: Ejercicio): Ejercicio {
        return try {
            val ejercicioExistente = ejercicioDAO.findById(id)
            if (ejercicioExistente.isPresent) {
                val ejercicio = ejercicioExistente.get()
                ejercicio.nombre = ejercicioActualizado.nombre
                ejercicio.descripcion = ejercicioActualizado.descripcion
                ejercicio.repeticiones = ejercicioActualizado.repeticiones
                ejercicio.peso = ejercicioActualizado.peso
                ejercicio.musculo = ejercicioActualizado.musculo
                ejercicioDAO.save(ejercicio)
            } else {
                throw NoSuchElementException("No se encontró el ejercicio con id: $id")
            }
        } catch (e: NoSuchElementException) {
            throw e
        } catch (e: Exception) {
            throw RuntimeException("Error al actualizar el ejercicio: ${e.message}")
        }
    }

    override fun eliminarEjercicio(id: String) {
        try {
            if (ejercicioDAO.existsById(id)) {
                ejercicioDAO.deleteById(id)
            } else {
                throw NoSuchElementException("No se encontró el ejercicio con id: $id")
            }
        } catch (e: NoSuchElementException) {
            throw e
        } catch (e: Exception) {
            throw RuntimeException("Error al eliminar el ejercicio: ${e.message}")
        }
    }
}