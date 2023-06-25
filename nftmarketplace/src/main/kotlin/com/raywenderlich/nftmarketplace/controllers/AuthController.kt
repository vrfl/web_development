package com.raywenderlich.nftmarketplace.controllers

import io.jsonwebtoken.Jwts
import com.raywenderlich.nftmarketplace.dtos.LoginDTO
import com.raywenderlich.nftmarketplace.dtos.Message
import com.raywenderlich.nftmarketplace.dtos.RegisterDTO
import com.raywenderlich.nftmarketplace.models.User
import com.raywenderlich.nftmarketplace.services.UserService
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("api")
class AuthController(private val userService: UserService) {
    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<User> {
        val user = User()
        user.name = body.name
        user.email = body.email
        user.password = body.password

        return ResponseEntity.ok(this.userService.save(user))
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any>{
    val user = this.userService.findByEmail(body.email)
        ?: return ResponseEntity.badRequest().body(Message("user not found!"))

    if (!user.comparePassword((body.password))) {
        return ResponseEntity.badRequest().body(Message("invalid password!"))
    }

    val issuer = user.id.toString()

    val jwt = Jwts.builder()
        .setIssuer(issuer)
        .setExpiration(Date(System.currentTimeMillis() + 3600 * 24 * 1000)) // 1 day
        .signWith(SignatureAlgorithm.ES512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)
        return ResponseEntity.ok(Message("success"))
    }
}