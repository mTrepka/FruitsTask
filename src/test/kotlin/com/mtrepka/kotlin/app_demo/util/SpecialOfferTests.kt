package com.mtrepka.kotlin.app_demo.util

import org.junit.jupiter.api.Test

class SpecialOfferTests {
    @Test
    fun everyTwoDiscountedHundredPercent() {
        val discountedValue = SpecialOffer.calculateSpecialOffer(20,1.0,2,100)
        assert(discountedValue == 10.0)

    }
    @Test
    fun everyThirdDiscountedHundredPercent() {
        val discountedValue = SpecialOffer.calculateSpecialOffer(20,2.0,3,100)
        assert(discountedValue == 28.0)
    }

    @Test
    fun everyThirdDiscountedFiftyPercent() {
        val discountedValue = SpecialOffer.calculateSpecialOffer(20,2.0,3,50)
        assert(discountedValue == 34.0)
    }

    @Test
    fun eachDiscountedFiftyPercent() {
        val discountedValue = SpecialOffer.calculateSpecialOffer(20,2.0,1,50)
        assert(discountedValue == 20.0)
    }
}