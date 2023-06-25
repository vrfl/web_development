package com.raywenderlich.nftmarketplace.services

import com.raywenderlich.nftmarketplace.datasource.BankDataSource
import com.raywenderlich.nftmarketplace.models.Bank
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest {
    // Если попытаться вызвать макет метода, который не знает, что вернуть,
    // и не установлен relaxed = true, то вернется исключение.

    private val dataSource: BankDataSource = mockk(relaxed = true)
    private val bankService = BankService(dataSource)
  @Test
  fun `should call its data source to retrieve banks`() {
  // when
    bankService.getBanks()

  // then
      verify(exactly = 1) { dataSource.retrieveBanks() }

  }

    @Test
    fun `should call data source to retrieve bank by account number`() {
    // given
        val accountNumber = "1212"

    // when
        bankService.getBank(accountNumber)

    // then
        verify(exactly = 1) { dataSource.retrieveBank(accountNumber) }
    }

    fun `should call data source to create new bank`() {
        // given
        val newBank = Bank("1440", 30.5, 1)

        // when
        bankService.addBank(newBank)

        // then
        verify(exactly = 1) { dataSource.createBank(newBank) }
    }

    fun `should call data source to update bank`() {
        // given
        val oldBankAccountNumber = "1423"
        val newBank = Bank("5622", 45.5, 2)

        // when
        bankService.updateBank(oldBankAccountNumber, newBank)

        // then
        verify(exactly = 1) { dataSource.updateBank(oldBankAccountNumber, newBank) }
    }

    fun `should call data source to delete bank`() {
        // given
        val accountNumber = "1212"

        // when
        bankService.deleteBank(accountNumber)

        // then
        verify(exactly = 1) { dataSource.deleteBank(accountNumber) }
    }
}