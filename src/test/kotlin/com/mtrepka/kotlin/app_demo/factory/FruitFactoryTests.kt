package com.mtrepka.kotlin.app_demo.factory

import com.mtrepka.kotlin.app_demo.dao.Apple
import com.mtrepka.kotlin.app_demo.dao.Fruit
import com.mtrepka.kotlin.app_demo.dao.Orange
import org.junit.jupiter.api.Test
import kotlin.reflect.typeOf

class FruitFactoryTests {
    private val APPLE_AMOUNT = 5
    private val ORANGE_AMOUNT = 5

    @Test
    fun getOrange()
    {
        val f = FruitFactory.getFruit("orange",ORANGE_AMOUNT)
        assert(f is Fruit)
        assert(f is Orange)
        assert(f !is Apple)
        assert(f!!.amount == ORANGE_AMOUNT)
    }

    @Test
    fun getApple()
    {
        val f = FruitFactory.getFruit("apple",APPLE_AMOUNT)
        assert(f is Fruit)
        assert(f is Apple)
        assert(f !is Orange)
        assert(f!!.amount == APPLE_AMOUNT)
    }

    @Test
    fun getAppleWrongAmount()
    {
        val f = FruitFactory.getFruit("apple",-1)
        assert(f== null)
        val f1 = FruitFactory.getFruit("apple",0)
        assert(f1== null)
    }
}