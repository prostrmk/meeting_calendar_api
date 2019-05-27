package com.itechart.webflux.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication(scanBasePackages = ["com.itechart.webflux.*"])
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = ["com.*"])
@EntityScan(basePackages = ["com.*"])
open class MeetingCalendar {



    @Autowired
    internal var mongoClient: ReactiveMongoDatabaseFactory? = null

    @Bean
    open fun reactiveMongoTemplate(): ReactiveMongoTemplate {
        return ReactiveMongoTemplate(mongoClient!!)
    }


}
fun main(args: Array<String>) {
    runApplication<MeetingCalendar>(*args)
//        SpringApplication.run(MeetingCalendar::class.java, *args)
}