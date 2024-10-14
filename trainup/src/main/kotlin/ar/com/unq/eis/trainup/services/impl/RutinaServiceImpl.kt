package ar.com.unq.eis.trainup.services.impl

import ar.com.unq.eis.trainup.dao.EjercicioDAO
import ar.com.unq.eis.trainup.dao.RutinaDAO
import ar.com.unq.eis.trainup.model.Ejercicio
import ar.com.unq.eis.trainup.model.Rutina
import ar.com.unq.eis.trainup.services.RutinaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RutinaServiceImpl : RutinaService {

    @Autowired
    lateinit var rutinaDAO: RutinaDAO
    @Autowired
    lateinit var ejercicioDAO: EjercicioDAO


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
                rutina.descripcion = rutinaActualizada.descripcion
                rutina.categoria = rutinaActualizada.categoria
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
    override fun agregarEjercicio(id: String, ejercicio: Ejercicio): Rutina {
        return try {
            val rutinaExistente = rutinaDAO.findById(id)
            if (rutinaExistente.isPresent) {
                val rutina = rutinaExistente.get()
                rutina.agregarEjercicio(ejercicioDAO.save(ejercicio))
                rutinaDAO.save(rutina)
            } else {
                throw NoSuchElementException("No se encontró la rutina con id: $id")
            }
        } catch (e: NoSuchElementException) {
            throw e
        } catch (e: Exception) {
            throw RuntimeException("Error al agregar ejercicio a la rutina: ${e.message}")
        }
    }
    override fun eliminarEjercicio(id: String, idEj: String): Rutina {
        return try {
            val rutinaExistente = rutinaDAO.findById(id)
            if (rutinaExistente.isPresent) {
                val rutina = rutinaExistente.get()
                rutina.eliminarEjercicio(idEj)
                rutinaDAO.save(rutina)
            } else {
                throw NoSuchElementException("No se encontró el ejercicio con id: $idEj")
            }
        } catch (e: NoSuchElementException) {
            throw e
        } catch (e: Exception) {
            throw RuntimeException("Error al eliminar ejercicio de la rutina: ${e.message}")
        }
    }

    override fun obtenerRutinasPorCategoria(categoria: String): List<Rutina> {

        return try {
            this.rutinaDAO.findByCategoria(categoria)
        } catch (e: Exception) {
            throw RuntimeException("Error al obtener la lista de rutinas por categoría: ${e.message}")
        }

    }

    override fun buscarRutinas(busqueda: String): List<Rutina> {
        val rutinas = obtenerRutinas()

        return rutinas.filter { rutina -> rutina.nombre.contains(busqueda,ignoreCase = true) }
    }
}