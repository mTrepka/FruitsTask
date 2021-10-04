package com.mtrepka.kotlin.app_demo.dao

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class OrderSummary(@Id val id: String = java.util.UUID.randomUUID().toString()) {
    var fruitList = mutableListOf<Fruit>()
    fun getValue(): Double {
        return fruitList.map { e -> e.getValue() }.sum()
    }
}
