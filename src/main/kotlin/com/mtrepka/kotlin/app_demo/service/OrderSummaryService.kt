package com.mtrepka.kotlin.app_demo.service

import com.mtrepka.kotlin.app_demo.dao.OrderSummary
import com.mtrepka.kotlin.app_demo.factory.FruitFactory
import com.mtrepka.kotlin.app_demo.repository.OrderSummaryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderSummaryService(val orderSummaryRepository: OrderSummaryRepository) {

    fun saveOrderSummary(order: Map<String, Int>): OrderSummary {
        val result = OrderSummary()
        for (entry in order) {
            val fruit = FruitFactory.getFruit(entry.key, entry.value)
            if (fruit != null) {
                result.fruitList.add(fruit)
            }
        }
        if (result.fruitList.isNotEmpty())
            orderSummaryRepository.save(result)
        return result
    }

    fun getOrderSummary(id: String): Optional<OrderSummary> {
        return orderSummaryRepository.findById(id)
    }

    fun getAll(): List<OrderSummary> {
        return orderSummaryRepository.findAll()
    }
}