package com.raywenderlich.nftmarketplace.exceptions

class BankAlreadyExistsException(accountNumber: String) : RuntimeException("Bank with $accountNumber already exist")