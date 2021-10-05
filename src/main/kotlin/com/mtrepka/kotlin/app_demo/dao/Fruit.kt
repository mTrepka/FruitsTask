package com.mtrepka.kotlin.app_demo.dao

abstract class Fruit(val amount: Int) {
    abstract fun getPrice(): Double

    fun getName(): String {
        return javaClass.simpleName
    }

    open fun getValue(): Double {
        return getPrice() * amount
    }
}