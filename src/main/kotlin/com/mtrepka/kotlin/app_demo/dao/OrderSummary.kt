package com.mtrepka.kotlin.app_demo.dao

class OrderSummary {
    var fruitList = mutableListOf<Fruit>()

    fun getValue(): Double {
        return fruitList.map { e -> e.getValue() }.sum()
    }
}
