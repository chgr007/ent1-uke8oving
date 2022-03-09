package com.groyp.ent1uke8oving.controllers

import com.groyp.ent1uke8oving.model.UserEntity
import com.groyp.ent1uke8oving.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class UserController(
    @Autowired private val userService: UserService
) {

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<UserEntity>> {

        val users = userService.getUsers()
        return ResponseEntity.ok().body(users)
    }
}