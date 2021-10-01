package com.mtrepka.kotlin.app_demo.service

import com.mtrepka.kotlin.app_demo.dao.OrderSummary
import com.mtrepka.kotlin.app_demo.factory.FruitFactory
import org.springframework.stereotype.Service

@Service
class FruitService {
    fun getOrderSummary(order:Map<String, Int>) : OrderSummary {
        val result = OrderSummary()
        for ( entry in order) {
            val fruit = FruitFactory.getFruit(entry.key,entry.value)
            if (fruit!=null) {
                result.fruitList.add(fruit)
            }
        }
        return result
    }
}