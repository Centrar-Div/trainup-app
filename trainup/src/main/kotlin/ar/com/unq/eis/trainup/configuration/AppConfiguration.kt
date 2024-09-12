package ar.com.unq.eis.trainup.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory

@Configuration
class AppConfiguration {

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val mongoUri = "mongodb+srv://elian21:LlSM8VhaFcR4i9ZA@cluster0.oljii16.mongodb.net/trainup?retryWrites=true&w=majority"
        return MongoTemplate(SimpleMongoClientDatabaseFactory(mongoUri))
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5173")
                    .allowedMethods("*")
                    .allowedHeaders("*")
            }
        }
    }
}
