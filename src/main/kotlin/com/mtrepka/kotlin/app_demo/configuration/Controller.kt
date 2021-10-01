package com.mtrepka.kotlin.app_demo.configuration

import com.mtrepka.kotlin.app_demo.dao.OrderSummary
import com.mtrepka.kotlin.app_demo.service.FruitService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class Controller(val fruitService: FruitService) {

    @PostMapping()
    fun postOrderSummary(@RequestBody body: Map<String, Int>): OrderSummary {
        return fruitService.getOrderSummary(body)
    }
}