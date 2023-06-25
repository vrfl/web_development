package com.raywenderlich.nftmarketplace.controllers

import com.raywenderlich.nftmarketplace.models.Bank
import com.raywenderlich.nftmarketplace.services.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()
}