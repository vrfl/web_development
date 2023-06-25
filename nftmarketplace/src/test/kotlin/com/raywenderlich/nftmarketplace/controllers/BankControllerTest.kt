package com.raywenderlich.nftmarketplace.controllers

import com.google.gson.Gson
import com.raywenderlich.nftmarketplace.models.Bank
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc
    private val baseUrl = "/api/banks"
    val gson = Gson()
    @Test
    fun `should return all banks`() {

    // when/ then
        mockMvc.get(baseUrl)
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].accountNumber") { value("1234")}
            }
    }

    @Test
    fun `should return bank on account number`() {
        val bank = Bank("1234", 3.14, 17)

        mockMvc.get("$baseUrl/${bank.accountNumber}")
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(gson.toJson(bank), true)
                }
            }.andDo { print() }
    }

    @Test
    fun `should add new bank`() {
        val newBank = Bank("4668", 5.15, 45)
    val newBankJson = gson.toJson(newBank)
        mockMvc.perform(post("$baseUrl")
            .contentType(MediaType.APPLICATION_JSON)
            .content("$newBankJson"))
            .andExpect (status().isCreated())
            .andExpect(content().string(newBankJson))

        mockMvc.get("$baseUrl/${newBank.accountNumber}")
            .andExpect {
                status { isOk() }
                content {
                    newBankJson
                }
            }.andDo { print() }
    }

    @Test
    fun `should update bank`() {
        val updatedBank = Bank("1234", 2.2, 17)

        mockMvc.perform(patch("$baseUrl/${updatedBank.accountNumber}")
            .contentType(MediaType.APPLICATION_JSON)
            .content(gson.toJson(updatedBank)))
            .andExpect (status().isOk())
            .andExpect(content().string(gson.toJson(updatedBank)))

        mockMvc.get("$baseUrl/${updatedBank.accountNumber}")
            .andExpect {
                status { isOk() }
                content {
                    gson.toJson(updatedBank)
                }
            }.andDo { print() }
    }

    @Test
    fun `should delete bank with given account number`() {
        val accountNumber = 1234

        mockMvc.delete("$baseUrl/$accountNumber")
            .andExpect {
                status { isNoContent() }
            }
    }
}