package com.mtrepka.kotlin.app_demo.configuration

import com.fasterxml.jackson.databind.json.JsonMapper
import com.mtrepka.kotlin.app_demo.repository.OrderSummaryRepository
import com.mtrepka.kotlin.app_demo.service.OrderSummaryService
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
@ConfigurationProperties(prefix = "test.data")
class TestDataConfiguration(
    val orderSummaryService: OrderSummaryService,
    val orderSummaryRepository: OrderSummaryRepository
) {
    var enabled: Boolean = false
    val logger = LoggerFactory.getLogger(TestDataConfiguration::class.java)

    @Bean
    fun initData() {
        if (enabled) {
            orderSummaryRepository.deleteAll()
            logger.info("Initial data enabled")
            val f = File("src/main/resources/testData.json")
            val mapper = JsonMapper()
            if (f.canRead()) {
                f.readLines().map { mapper.readValue(it, Map::class.java) }
                    .forEach { orderSummaryService.saveOrderSummary(it as Map<String, Int>) }
            }
        }
    }
}