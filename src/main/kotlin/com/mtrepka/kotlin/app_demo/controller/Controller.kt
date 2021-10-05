package com.mtrepka.kotlin.app_demo.controller


import com.mtrepka.kotlin.app_demo.dao.OrderSummary
import com.mtrepka.kotlin.app_demo.service.OrderSummaryService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping()
class Controller(val orderSummaryService: OrderSummaryService) {

    @PostMapping("/order/")
    fun postOrderSummary(@RequestBody body: Map<String, Int>): Any {
        return try {
            orderSummaryService.saveOrderSummary(body)
        } catch (exception: Exception) {
            """{"message":"${exception.message}"}"""
        }

    }

    @GetMapping("/order/")
    fun getOrderSummary(@RequestParam(required = false) id: String?): List<OrderSummary?> {
        return if (id == null) {
            orderSummaryService.getAll()
        } else {
            listOf(orderSummaryService.getOrderSummary(id))
        }
    }

    @GetMapping("/order/sold-amount/")
    fun getSoldAmount(): Map<String, Int> {
        return orderSummaryService.getSellAmount()
    }

    @GetMapping("/order/sold-discount-amount/")
    fun getSoldDiscountAmount(): Map<String, Double> {
        return orderSummaryService.getSoldDiscountAmount()
    }

    @GetMapping("/order/max-value/")
    fun getMaxValue(): OrderSummary? {
        return orderSummaryService.getMaxValue()
    }

    @GetMapping("/order/min-value/")
    fun getMinValue(): OrderSummary? {
        return orderSummaryService.getMinValue()
    }

}