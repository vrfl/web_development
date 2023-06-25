package com.raywenderlich.nftmarketplace.datasource.mock

import com.raywenderlich.nftmarketplace.datasource.BankDataSource
import com.raywenderlich.nftmarketplace.exceptions.BankAlreadyExistsException
import com.raywenderlich.nftmarketplace.exceptions.BankNotExistException
import com.raywenderlich.nftmarketplace.models.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    private val banks = mutableListOf<Bank>(
        Bank("1234", 3.14,17),
        Bank("1010", 17.0, 0),
        Bank("5678", 0.0, 100)
    )
    override fun retrieveBanks(): Collection<Bank> {
        return banks
    }

    override fun retrieveBank(accountNumber: String): Bank {
        return banks.firstOrNull { it.accountNumber == accountNumber }
            ?: throw BankNotExistException(accountNumber)
    }

    override fun createBank(bank: Bank): Bank {
        if(banks.any { it.accountNumber == bank.accountNumber }) {
            throw BankAlreadyExistsException(bank.accountNumber)
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(accountNumber: String, bank: Bank): Bank {
        val index = banks.indexOfFirst { it.accountNumber == accountNumber }.takeIf { it >= 0 }
            ?: throw BankNotExistException(bank.accountNumber)
        banks[index] = bank
        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val bank = banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw BankNotExistException(accountNumber)
        banks.remove(bank)
    }


}