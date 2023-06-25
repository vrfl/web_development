package com.raywenderlich.nftmarketplace.repositories

import com.raywenderlich.nftmarketplace.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>{
    fun findByEmail(email: String): User?
}