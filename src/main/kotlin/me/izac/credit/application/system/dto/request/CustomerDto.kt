package me.izac.credit.application.system.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.izac.credit.application.system.entity.Address
import me.izac.credit.application.system.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
  @field:NotEmpty(message = "Field cannot be empty") val firstName: String,
  @field:NotEmpty(message = "Field cannot be empty") val lastName: String,
  @field:NotEmpty(message = "Invalid input for CPF")
  @field:CPF(message = "This invalid CPF") val cpf: String,
  @field:NotNull(message = "Invaid input. Must be a number")
  @field:Min(message = "Must be greather than 0", value = 0) val income: BigDecimal,
  @field:Email(message = "Invalid email format")
  @field:NotEmpty(message = "Field cannot be empty") val email: String,
  @field:NotEmpty(message = "Field cannot be empty") val password: String,
  @field:NotEmpty(message = "Field empty") val zipCode: String,
  @field:NotEmpty(message = "Field empty") val street: String
) {

  fun toEntity(): Customer = Customer(
      firstName = this.firstName,
      lastName = this.lastName,
      cpf = this.cpf,
      income = this.income,
      email = this.email,
      password = this.password,
      address = Address(
          zipCode = this.zipCode,
          street = this.street
      )
  )
}
