package com.raywenderlich.nftmarketplace.models

data class Bank (
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Int
)