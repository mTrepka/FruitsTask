package com.mtrepka.kotlin.app_demo.util

import kotlin.math.abs


object SpecialOffer {

    fun calculateSpecialOffer(amount: Int, value: Double, discountOn: Int, discountPercent: Int): Double {
        val amountDiscounted = amount / discountOn
        val notDiscounted = amount - amountDiscounted
        return notDiscounted * value + amountDiscounted * abs(discountPercent - 100) / 100 * value
    }
}