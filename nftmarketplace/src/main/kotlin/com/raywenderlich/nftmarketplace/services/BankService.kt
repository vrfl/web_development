package com.raywenderlich.nftmarketplace.services

import com.raywenderlich.nftmarketplace.datasource.BankDataSource
import com.raywenderlich.nftmarketplace.models.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
}