package ar.com.unq.eis.trainup.services.impl

import ar.com.unq.eis.trainup.dao.RutinaDAO
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.services.RutinaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RutinaServiceImpl(
    @Autowired
    private val rutinaDAO: RutinaDAO
) : RutinaService {

    override fun crearRutina(rutina: Rutina): Rutina {
        return try {
            rutinaDAO.save(rutina)
        } catch (e: Exception) {
            throw RuntimeException("Error al crear la rutina: ${e.message}")
        }
    }

    override fun obtenerRutinas(): List<Rutina> {
        return try {
            rutinaDAO.findAll()
        } catch (e: Exception) {
            throw RuntimeException("Error al obtener la lista de rutinas: ${e.message}")
        }
    }

    override fun obtenerRutinaPorId(id: String): Rutina {
        return try {
            val rutina = rutinaDAO.findById(id)
            if (rutina.isPresent) {
                rutina.get()
            } else {
                throw NoSuchElementException("No se encontró la rutina con id: $id")
            }
        } catch (e: NoSuchElementException) {
            throw e
        } catch (e: Exception) {
            throw RuntimeException("Error al buscar la rutina por id: ${e.message}")
        }
    }

    override fun actualizarRutina(id: String, rutinaActualizada: Rutina): Rutina {
        return try {
            val rutinaExistente = rutinaDAO.findById(id)
            if (rutinaExistente.isPresent) {
                val rutina = rutinaExistente.get()
                rutina.nombre = rutinaActualizada.nombre
                rutina.ejercicios = rutinaActualizada.ejercicios
                rutinaDAO.save(rutina)
            } else {
                throw NoSuchElementException("No se encontró la rutina con id: $id")
            }
        } catch (e: NoSuchElementException) {
            throw e
        } catch (e: Exception) {
            throw RuntimeException("Error al actualizar la rutina: ${e.message}")
        }
    }

    override fun eliminarRutina(id: String) {
        try {
            if (rutinaDAO.existsById(id)) {
                rutinaDAO.deleteById(id)
            } else {
                throw NoSuchElementException("No se encontró la rutina con id: $id")
            }
        } catch (e: NoSuchElementException) {
            throw e
        } catch (e: Exception) {
            throw RuntimeException("Error al eliminar la rutina: ${e.message}")
        }
    }
}