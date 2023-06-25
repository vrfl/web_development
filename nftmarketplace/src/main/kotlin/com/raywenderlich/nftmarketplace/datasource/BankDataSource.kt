package com.raywenderlich.nftmarketplace.datasource

import com.raywenderlich.nftmarketplace.models.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>

    fun retrieveBank(accountNumber: String): Bank

    fun createBank(bank: Bank): Bank

    fun updateBank(accountNumber: String, bank: Bank): Bank

    fun deleteBank(accountNumber: String)
}