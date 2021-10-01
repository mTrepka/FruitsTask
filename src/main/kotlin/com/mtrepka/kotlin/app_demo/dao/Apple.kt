package com.mtrepka.kotlin.app_demo.dao

class Apple(amount: Int) : Fruit(amount = amount){
    override fun getPrice(): Double {
        return 0.60
    }
}