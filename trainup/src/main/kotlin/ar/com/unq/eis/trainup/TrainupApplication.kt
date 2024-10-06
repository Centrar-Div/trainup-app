package ar.com.unq.eis.trainup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories




@SpringBootApplication
@EnableMongoRepositories
class TrainupApplication

			fun main(args: Array<String>) {
	runApplication<TrainupApplication>(*args)
}


