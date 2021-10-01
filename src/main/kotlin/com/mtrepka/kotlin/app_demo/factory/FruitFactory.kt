package com.mtrepka.kotlin.app_demo.factory

import com.mtrepka.kotlin.app_demo.dao.Apple
import com.mtrepka.kotlin.app_demo.dao.Fruit
import com.mtrepka.kotlin.app_demo.dao.Orange

object FruitFactory {

    fun getFruit(name: String, amount: Int): Fruit? {
        if (amount < 1) return null
        return when(name.toLowerCase()) {
            "orange" -> Orange(amount)
            "apple" -> Apple(amount)
            else -> null
        }
    }
}