package com.raywenderlich.nftmarketplace.controllers

import com.raywenderlich.nftmarketplace.models.Bank
import com.raywenderlich.nftmarketplace.services.BankService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(private val service: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> = service.getBanks()
    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) = service.getBank(accountNumber)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank) = service.addBank(bank)

    @PatchMapping("/{accountNumber}")
    fun updateBank(@PathVariable accountNumber: String, @RequestBody bank: Bank) =
        service.updateBank(accountNumber, bank)

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable accountNumber: String) = service.deleteBank(accountNumber)
}