package com.raywenderlich.nftmarketplace.services

import com.raywenderlich.nftmarketplace.models.User
import com.raywenderlich.nftmarketplace.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun save(user: User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }

}