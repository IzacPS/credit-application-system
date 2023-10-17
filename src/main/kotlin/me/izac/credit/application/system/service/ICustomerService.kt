package me.izac.credit.application.system.service

import me.izac.credit.application.system.entity.Customer

interface ICustomerService {
  fun save(customer: Customer): Customer
  fun findById(id: Long): Customer
  fun delete(id: Long)
}