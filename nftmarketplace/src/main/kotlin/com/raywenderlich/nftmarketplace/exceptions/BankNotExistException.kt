package com.raywenderlich.nftmarketplace.exceptions

class BankNotExistException(accountNumber: String) : RuntimeException("Bank with account number $accountNumber not exist")