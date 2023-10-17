package me.izac.credit.application.system.dto.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.izac.credit.application.system.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDto(
  @field:NotEmpty(message = "Field cannot be empty") val firstName: String,
  @field:NotEmpty(message = "Field cannot be empty") val lastName: String,
  @field:NotNull(message = "Field cannot be empty") val income: BigDecimal,
  @field:NotEmpty(message = "Field cannot be empty") val zipCode: String,
  @field:NotEmpty(message = "Field cannot be empty") val street: String
) {
  fun toEntity(customer: Customer): Customer {
    customer.firstName = this.firstName
    customer.lastName = this.lastName
    customer.income = this.income
    customer.address.street = this.street
    customer.address.zipCode = this.zipCode
    return customer
  }
}
