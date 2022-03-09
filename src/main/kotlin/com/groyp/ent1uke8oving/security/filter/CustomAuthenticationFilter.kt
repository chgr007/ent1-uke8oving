package com.groyp.ent1uke8oving.security.filter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.groyp.ent1uke8oving.security.jwt.JwtUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.bind.annotation.CookieValue
import javax.servlet.FilterChain
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(@Autowired private val authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {

    val logger = LoggerFactory.getLogger(this::class.java)

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val user = request?.getParameter("username")
        // header fields:
        // request?.getHeader("username")
        val password = request?.getParameter("password")
       response?.addCookie(Cookie("yooo", "bro"))
        //request?.cookies?.get(1)?.value
        val authenticationToken = UsernamePasswordAuthenticationToken(user, password)

        return authManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authentication: Authentication
    ) {
        val user: User = authentication?.principal as User
        val accessToken = JwtUtil.createToken(user, request?.requestURI.toString())
        val refreshToken = JwtUtil.createToken(user, request?.requestURI.toString())
        val tokens = mapOf("access_token" to accessToken, "refresh_token" to refreshToken)
        response?.contentType = APPLICATION_JSON_VALUE
        response?.addCookie(Cookie("acces_token", accessToken))
        jacksonObjectMapper().writeValue(response?.outputStream, tokens)
    }
}