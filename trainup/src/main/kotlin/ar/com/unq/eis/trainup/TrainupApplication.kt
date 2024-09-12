package ar.com.unq.eis.trainup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer



@SpringBootApplication
@EnableMongoRepositories
class TrainupApplication

fun main(args: Array<String>) {
	runApplication<TrainupApplication>(*args)
}


