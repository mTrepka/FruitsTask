package com.mtrepka.kotlin.app_demo.configuration


import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTests(@Autowired val restTemplate: TestRestTemplate) {
    val headers = HttpHeaders()
    val ORDER_SUMMARY_POST_URI = "/"

    @Test
    fun postOrderSummary_emptyMap() {
        val request = HttpEntity<String>("{}", headers)
        val predResponse = """{"fruitList":[],"value":0.0}"""
        val response = restTemplate.postForEntity<String>(ORDER_SUMMARY_POST_URI, request)
        assert(response.statusCode.is2xxSuccessful)
        assert(response.body == predResponse)
    }

    @Test
    fun postOrderSummary_correctData() {
        val testBody = """{"orange":4, "apple":22}"""
        val predResponse =
            """{"fruitList":[{"amount":4,"value":0.75,"price":0.25,"name":"Orange"},{"amount":22,"value":6.6,"price":0.6,"name":"Apple"}],"value":7.35}"""
        val request = HttpEntity<String>(testBody, headers)
        val response = restTemplate.postForEntity<String>(ORDER_SUMMARY_POST_URI, request)
        assert(response.statusCode.is2xxSuccessful)
        assert(response.body == predResponse)
    }

    @Test
    fun postOrderSummary_wrongData() {
        val testBody = """{"orange":0, "apple":-1, "onion":4}"""
        val predResponse = """{"fruitList":[],"value":0.0}"""
        val request = HttpEntity<String>(testBody, headers)
        val response = restTemplate.postForEntity<String>(ORDER_SUMMARY_POST_URI, request)
        assert(response.statusCode.is2xxSuccessful)
        println(response.body)
        assert(response.body == predResponse)
    }

    @Test
    fun postOrderSummary_partiallyCorrectData() {
        val testBody = """{"orange":4, "apple":-1, "onion":4}"""
        val predResponse = """{"fruitList":[{"amount":4,"value":0.75,"price":0.25,"name":"Orange"}],"value":0.75}"""
        val request = HttpEntity<String>(testBody, headers)
        val response = restTemplate.postForEntity<String>(ORDER_SUMMARY_POST_URI, request)
        assert(response.statusCode.is2xxSuccessful)
        assert(response.body == predResponse)
    }

    init {
        headers.contentType = MediaType.APPLICATION_JSON
    }
}