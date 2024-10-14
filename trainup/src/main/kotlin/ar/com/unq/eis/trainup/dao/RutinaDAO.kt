package ar.com.unq.eis.trainup.dao

import ar.com.unq.eis.trainup.model.Rutina
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RutinaDAO : MongoRepository<Rutina, String> {

    @Query(
        "{ 'categoria' : ?0 }"
    )
    fun findByCategoria(categoria: String): List<Rutina>
}