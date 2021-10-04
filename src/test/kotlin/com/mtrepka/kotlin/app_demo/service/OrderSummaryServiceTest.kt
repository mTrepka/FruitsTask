package com.mtrepka.kotlin.app_demo.service

import com.mtrepka.kotlin.app_demo.dao.OrderSummary
import com.mtrepka.kotlin.app_demo.factory.FruitFactory
import com.mtrepka.kotlin.app_demo.repository.OrderSummaryRepository
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito


internal class OrderSummaryServiceTest {
    @Test
    fun saveOrderSummary_success() {
        val repo = Mockito.mock(OrderSummaryRepository::class.java)
        Mockito.`when`(repo.save(any())).thenAnswer { i -> i.arguments[0] }
        val service = OrderSummaryService(repo)

        val map = mapOf("orange" to 5)

        val result = service.saveOrderSummary(map)

        assert(result.fruitList.size == 1)
        assert(result.fruitList[0].amount == 5)
        assert(result.fruitList[0].getName().lowercase() == "orange")
        assert(result.getValue() == result.fruitList.map { e -> e.getValue() }.sum())
    }

    @Test
    fun saveOrderSummary_emptyBody() {
        val repo = Mockito.mock(OrderSummaryRepository::class.java)
        Mockito.`when`(repo.save(any())).thenAnswer { i -> i.arguments[0] }
        val service = OrderSummaryService(repo)

        val map = emptyMap<String,Int>()

        val result = service.saveOrderSummary(map)
        assert(result.fruitList.size == 0)
        assert(result.getValue() == 0.0)
    }

    @Test
    fun saveOrderSummary_wrongData() {
        val repo = Mockito.mock(OrderSummaryRepository::class.java)
        Mockito.`when`(repo.save(any())).thenAnswer { i -> i.arguments[0] }
        val service = OrderSummaryService(repo)

        val map = mapOf("onion" to 5,"engine" to 100,"apple" to -100,"orange" to 0)

        val result = service.saveOrderSummary(map)
        assert(result.fruitList.size == 0)
        assert(result.getValue() == 0.0)
    }

    @Test
    fun saveOrderSummary_partiallyOkData() {
        val repo = Mockito.mock(OrderSummaryRepository::class.java)
        Mockito.`when`(repo.save(any())).thenAnswer { i -> i.arguments[0] }
        val service = OrderSummaryService(repo)

        val map = mapOf("onion" to 5,"engine" to 100,"apple" to -100,"orange" to 1)

        val result = service.saveOrderSummary(map)
        assert(result.fruitList.size == 1)
        assert(result.fruitList[0].amount == 1)
        assert(result.fruitList[0].getName().lowercase() == "orange")
        assert(result.getValue() == result.fruitList.map { e -> e.getValue() }.sum())
    }

}