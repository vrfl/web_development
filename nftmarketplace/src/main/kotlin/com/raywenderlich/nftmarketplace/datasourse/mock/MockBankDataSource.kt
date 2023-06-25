package com.raywenderlich.nftmarketplace.datasourse.mock

import com.raywenderlich.nftmarketplace.datasourse.BankDataSource
import com.raywenderlich.nftmarketplace.models.Bank

class MockBankDataSource : BankDataSource {

    private val banks = listOf(
        Bank("1234", 3.14,17),
        Bank("1010", 17.0, 0),
        Bank("5678", 0.0, 100)
    )
    override fun retrieveBanks(): Collection<Bank> {
        return banks
    }
}