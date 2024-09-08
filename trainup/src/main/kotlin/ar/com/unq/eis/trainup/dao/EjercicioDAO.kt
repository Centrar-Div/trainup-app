package ar.com.unq.eis.trainup.dao

import ar.com.unq.eis.trainup.model.Ejercicio
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EjercicioDAO : MongoRepository<Ejercicio, String> {
}