package com.mtrepka.kotlin.app_demo.configuration

import com.mtrepka.kotlin.app_demo.dao.OrderSummary
import com.mtrepka.kotlin.app_demo.service.OrderSummaryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/order/")
class Controller(val orderSummaryService: OrderSummaryService) {

    @PostMapping
    fun postOrderSummary(@RequestBody body: Map<String, Int>): OrderSummary {
        return orderSummaryService.saveOrderSummary(body)
    }

    @GetMapping
    fun getOrderSummary(@RequestParam id: String) :ResponseEntity<OrderSummary> {
        return ResponseEntity.of( orderSummaryService.getOrderSummary(id))
    }

    @GetMapping("/all/")
    fun getAllOrderSummary() :List<OrderSummary> {
        return orderSummaryService.getAll()
    }
}