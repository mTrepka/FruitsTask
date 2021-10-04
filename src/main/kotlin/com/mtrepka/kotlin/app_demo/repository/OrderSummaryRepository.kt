package com.mtrepka.kotlin.app_demo.repository

import com.mtrepka.kotlin.app_demo.dao.OrderSummary
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderSummaryRepository : MongoRepository<OrderSummary,String>{
}