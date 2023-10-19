package me.izac.credit.application.system.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import me.izac.credit.application.system.dto.request.CreditDto
import me.izac.credit.application.system.dto.request.CustomerDto
import me.izac.credit.application.system.dto.response.CreditView
import me.izac.credit.application.system.dto.response.CreditViewList
import me.izac.credit.application.system.entity.Credit
import me.izac.credit.application.system.entity.Customer
import me.izac.credit.application.system.enummeration.Status
import me.izac.credit.application.system.repository.CreditRepository
import me.izac.credit.application.system.repository.CustomerRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.lang.reflect.TypeVariable
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CreditResourceTest {
    @Autowired
    private lateinit var customerRepository: CustomerRepository
    @Autowired
    private lateinit var creditRepository: CreditRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/credits"
    }



    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun `should create a credit and return 201 status`() {
        //given
        val c = customerRepository.save(builderCustomerDto().toEntity())

        val creditDto = builderCreditDTO(customerId = c.id!!)

        val valueAsString: String = objectMapper.writeValueAsString(creditDto)

        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(valueAsString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun `should except on wrong input from CreditDTO`(){
        val creditStr = "{\"creditValue\":0,\"dayFirstOfInstallment\":\"2023-10-18\",\"numberOfInstallments\":0,\"customerId\":1}"

        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(creditStr)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.details.numberOfInstallments").isNotEmpty)
            .andExpect(MockMvcResultMatchers.jsonPath("$.details.dayFirstOfInstallment").isNotEmpty)
            .andDo(MockMvcResultHandlers.print()).andReturn()
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun `should find credits by customer id`() {
        val c = customerRepository.save(builderCustomerDto().toEntity())

        val creditDto1 = builderCreditDTO(customerId = c.id!!)
        val creditDto2 = builderCreditDTO2(customerId = c.id!!)

        creditRepository.save(creditDto1.toEntity().apply { customer = c })
        creditRepository.save(creditDto2.toEntity().apply { customer = c })


        val result = mockMvc.perform(
            MockMvcRequestBuilders.get(URL)
                .param("customerId", c.id.toString())
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print()).andReturn()

        val list : List<CreditViewList> = objectMapper.readValue(result.response.contentAsString)
        assertFalse(list.isEmpty())
        assertEquals(2, list.size)
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    fun `should find credit by credit code`() {
        val c = customerRepository.save(builderCustomerDto().toEntity())

        val creditDto = builderCreditDTO(customerId = c.id!!)

        val credit = creditRepository.save(creditDto.toEntity().apply { customer = c })
        val cv = CreditView(credit)

        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${credit.creditCode}")
                .param("customerId", c.id.toString())
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print()).andReturn()

        val creditView: CreditView = objectMapper.readValue(result.response.contentAsString)
        assertEquals(cv.creditCode, creditView.creditCode)
        assertEquals(cv.creditValue.toDouble(), creditView.creditValue.toDouble())
        assertEquals(cv.numberOfInstallment, creditView.numberOfInstallment)
        assertEquals(cv.status, creditView.status)
        assertEquals(cv.emailCustomer, creditView.emailCustomer)
        assertEquals(cv.incomeCustomer!!.toDouble(), creditView.incomeCustomer!!.toDouble())
    }

    private fun builderCustomerDto(
        firstName: String = "Cami",
        lastName: String = "Cavalcante",
        cpf: String = "28475934625",
        email: String = "camila@email.com",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        password: String = "1234",
        zipCode: String = "000000",
        street: String = "Rua da Cami, 123",
    ) = CustomerDto(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        income = income,
        password = password,
        zipCode = zipCode,
        street = street
    )

    private fun builderCreditDTO(
        creditValue: BigDecimal = BigDecimal.valueOf(500.00),
        dayFirstOfInstallment: LocalDate = LocalDate.now().plusMonths(2)!!,
        numberOfInstallments: Int = 5,
        customerId: Long = 1,
    ) = CreditDto(
        creditValue = creditValue,
        dayFirstOfInstallment = dayFirstOfInstallment,
        numberOfInstallments = numberOfInstallments,
        customerId = customerId)

    private fun builderCreditDTO2(
        creditValue: BigDecimal = BigDecimal.valueOf(200.00),
        dayFirstOfInstallment: LocalDate = LocalDate.now().plusMonths(1)!!,
        numberOfInstallments: Int = 2,
        customerId: Long = 1,
    ) = CreditDto(
        creditValue = creditValue,
        dayFirstOfInstallment = dayFirstOfInstallment,
        numberOfInstallments = numberOfInstallments,
        customerId = customerId)

}