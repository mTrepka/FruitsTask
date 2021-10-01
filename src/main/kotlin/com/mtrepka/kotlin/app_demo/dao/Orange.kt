package com.mtrepka.kotlin.app_demo.dao

class Orange(amount: Int) : Fruit(amount = amount) {
    override fun getPrice(): Double {
        return 0.25
    }
}