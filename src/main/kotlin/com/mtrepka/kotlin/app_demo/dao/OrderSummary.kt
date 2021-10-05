package com.mtrepka.kotlin.app_demo.dao

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class OrderSummary(@Id val id: String = UUID.randomUUID().toString()) {
    var fruitList = mutableListOf<Fruit>()
    fun getValue(): Double {
        return fruitList.map { e -> e.getValue() }.sum()
    }
}
