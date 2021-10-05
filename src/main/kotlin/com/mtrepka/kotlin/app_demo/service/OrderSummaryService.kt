package com.mtrepka.kotlin.app_demo.service

import com.mtrepka.kotlin.app_demo.dao.OrderSummary
import com.mtrepka.kotlin.app_demo.factory.FruitFactory
import com.mtrepka.kotlin.app_demo.repository.OrderSummaryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

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

    fun getOrderSummary(id: String): OrderSummary? {
        return orderSummaryRepository.findByIdOrNull(id)
    }

    fun getAll(): List<OrderSummary> {
        return orderSummaryRepository.findAll()
    }

    fun getSellAmount(): Map<String, Int> {
        return orderSummaryRepository.findAll()
            .flatMap { it.fruitList }
            .groupBy { it.getName() }
            .mapValues { it.value.sumOf { e -> e.amount } }
    }

    fun getSoldDiscountAmount(): Map<String, Double> {
        return orderSummaryRepository.findAll()
            .flatMap { it.fruitList }
            .groupBy { it.getName() }
            .mapValues { it.value.sumOf { it.amount - it.getValue() / it.getPrice() } }
    }

    fun getMinValue(): OrderSummary? {
        return orderSummaryRepository.findAll()
            .minByOrNull { it.getValue() }
    }

    fun getMaxValue(): OrderSummary? {
        return orderSummaryRepository.findAll()
            .maxByOrNull { it.getValue() }
    }
}