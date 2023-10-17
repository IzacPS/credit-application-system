package me.izac.credit.application.system.dto.request

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import me.izac.credit.application.system.entity.Credit
import me.izac.credit.application.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
  @field:NotNull(message = "Field cannot be empty") val creditValue: BigDecimal,
  @field:Future val dayFirstOfInstallment: LocalDate,
  @field:Min(message = "Value must be greater than 0", value = 1)
  @field:Max(message = "Value must be less than 49", value = 48) val numberOfInstallments: Int,
  @field:NotNull(message = "Field cannot be empty") val customerId: Long
) {

  fun toEntity(): Credit = Credit(
      creditValue = this.creditValue,
      dayFirstInstallment = this.dayFirstOfInstallment,
      numberOfInstallments = this.numberOfInstallments,
      customer = Customer(id = this.customerId)
  )
}
