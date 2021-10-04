package com.mtrepka.kotlin.app_demo.configuration


import com.mtrepka.kotlin.app_demo.dao.OrderSummary
import com.mtrepka.kotlin.app_demo.factory.FruitFactory
import com.mtrepka.kotlin.app_demo.repository.OrderSummaryRepository
import com.mtrepka.kotlin.app_demo.service.OrderSummaryService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerTests(
    @Autowired val restTemplate: TestRestTemplate, @Autowired val orderSummaryService: OrderSummaryService,
    @Autowired val orderSummaryRepository: OrderSummaryRepository
) {
    val headers = HttpHeaders()
    var orderSummaryId: String = ""
    val ORDER_SUMMARY_POST_URI = "/order/"

    @Test
    fun postOrderSummary_emptyMap() {
        val request = HttpEntity<String>("{}", headers)
        val predResponse = arrayOf(
            """"fruitList":[]""",
            """"value":0.0""", """"id"""
        )
        val response = restTemplate.postForEntity<String>(ORDER_SUMMARY_POST_URI, request)
        assert(response.statusCode.is2xxSuccessful)
        predResponse.forEach { e -> assert(response.body!!.contains(e)) }
    }

    @Test
    fun postOrderSummary_correctData() {
        val testBody = """{"orange":4, "apple":22}"""
        val predResponse = arrayOf(
            """{"amount":4,"value":0.75,"price":0.25,"name":"Orange"},""",
            """{"amount":22,"value":6.6,"price":0.6,"name":"Apple"}""",
            """"value":7.35"""
        )
        val request = HttpEntity<String>(testBody, headers)
        val response = restTemplate.postForEntity<String>(ORDER_SUMMARY_POST_URI, request)
        assert(response.statusCode.is2xxSuccessful)
        predResponse.forEach { e -> assert(response.body!!.contains(e)) }
    }

    @Test
    fun postOrderSummary_wrongData() {
        val testBody = """{"orange":0, "apple":-1, "onion":4}"""
        val predResponse = arrayOf(
            """"fruitList":[]""",
            """"value":0.0""", """"id"""
        )
        val request = HttpEntity<String>(testBody, headers)
        val response = restTemplate.postForEntity<String>(ORDER_SUMMARY_POST_URI, request)
        assert(response.statusCode.is2xxSuccessful)
        predResponse.forEach { e -> assert(response.body!!.contains(e)) }
    }

    @Test
    fun postOrderSummary_partiallyCorrectData() {
        val testBody = """{"orange":4, "apple":-1, "onion":4}"""
        val predResponse = arrayOf(
            """{"amount":4,"value":0.75,"price":0.25,"name":"Orange"}""",
            """"value":0.75"""
        )
        val request = HttpEntity<String>(testBody, headers)
        val response = restTemplate.postForEntity<String>(ORDER_SUMMARY_POST_URI, request)
        assert(response.statusCode.is2xxSuccessful)
        predResponse.forEach { e -> assert(response.body!!.contains(e)) }
    }

    @Test
    fun getOrderSummary_correctId() {
        val ADDITIONAL_ARGUMENTS = "id=$orderSummaryId"
        val predResponse =
            """{"id":"$orderSummaryId","fruitList":[{"amount":5,"value":1.0,"price":0.25,"name":"Orange"}],"value":1.0}"""
        val request = HttpEntity<String>(headers)
        val response = restTemplate.getForEntity<String>("$ORDER_SUMMARY_POST_URI?$ADDITIONAL_ARGUMENTS", request)
        assert(response.statusCode.is2xxSuccessful)
        println(response.body)
        assert(predResponse == response.body)
    }

    @Test
    fun getOrderSummary_wrongId() {
        val ADDITIONAL_ARGUMENTS = "id=0"
        val request = HttpEntity<String>(headers)
        val response = restTemplate.getForEntity<String>("$ORDER_SUMMARY_POST_URI?$ADDITIONAL_ARGUMENTS", request)
        assert(response.statusCode.is4xxClientError)
    }

    @Test
    fun getAllOrderSummary() {
        val ADDITIONAL_URI = "all/"
        val request = HttpEntity<String>(headers)
        val response = restTemplate.getForEntity<String>("$ORDER_SUMMARY_POST_URI$ADDITIONAL_URI", request)
        assert(response.statusCode.is2xxSuccessful)
    }

    @BeforeAll
    fun init() {
        cleanDbs()
        initOrderSummary()
    }

    fun initOrderSummary() {
        val os = OrderSummary()
        os.fruitList.add(FruitFactory.getFruit("orange", 5)!!)
        orderSummaryRepository.save(os)
        orderSummaryId = os.id
    }

    fun cleanDbs() {
        orderSummaryRepository.deleteAll()
    }

    init {
        headers.contentType = MediaType.APPLICATION_JSON
    }
}