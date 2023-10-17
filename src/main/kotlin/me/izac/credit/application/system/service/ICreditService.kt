package me.izac.credit.application.system.service

import me.izac.credit.application.system.entity.Credit
import java.util.*

interface ICreditService {
  fun save(credit: Credit): Credit
  fun findAllByCustomer(customerId: Long): List<Credit>
  fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}