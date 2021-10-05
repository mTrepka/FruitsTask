package com.mtrepka.kotlin.app_demo.dao

import com.mtrepka.kotlin.app_demo.util.SpecialOffer

class Apple(amount: Int) : Fruit(amount = amount) {
    override fun getPrice(): Double {
        return 0.60
    }

    override fun getValue(): Double {
        return SpecialOffer.calculateSpecialOffer(amount, getPrice(), 2, 100)
    }
}