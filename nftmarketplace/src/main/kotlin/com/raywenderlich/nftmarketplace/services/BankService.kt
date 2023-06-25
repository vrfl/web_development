package com.raywenderlich.nftmarketplace.services

import com.raywenderlich.nftmarketplace.datasource.BankDataSource
import com.raywenderlich.nftmarketplace.models.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()

    fun getBank(accountNumber: String) = dataSource.retrieveBank(accountNumber)

    fun addBank(bank: Bank) = dataSource.createBank(bank)

    fun updateBank(accountNumber: String, bank: Bank) = dataSource.updateBank(accountNumber, bank)

    fun deleteBank(accountNumber: String) = dataSource.deleteBank(accountNumber)
}