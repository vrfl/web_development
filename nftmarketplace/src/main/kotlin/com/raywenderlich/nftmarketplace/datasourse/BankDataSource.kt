package com.raywenderlich.nftmarketplace.datasourse

import com.raywenderlich.nftmarketplace.models.Bank

interface BankDataSource {
    fun retrieveBanks(): Collection<Bank>
}