package com.mtrepka.kotlin.app_demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AppDemoApplication

fun main(args: Array<String>) {
    runApplication<AppDemoApplication>(*args)
}
