package ar.com.unq.eis.trainup.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory

@Configuration
class AppConfiguration {

    @Bean
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(SimpleMongoClientDatabaseFactory("mongodb+srv://elian21:LlSM8VhaFcR4i9ZA@cluster0.oljii16.mongodb.net/?retryWrites=true&w=majority"))
    }
}