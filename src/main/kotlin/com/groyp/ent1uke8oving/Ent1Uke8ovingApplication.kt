package com.groyp.ent1uke8oving

import com.groyp.ent1uke8oving.model.AuthorityEntity
import com.groyp.ent1uke8oving.service.AuthorityService
import com.groyp.ent1uke8oving.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class Ent1Uke8ovingApplication {
	@Bean
	fun init(@Autowired userService: UserService, @Autowired authorityService: AuthorityService) = CommandLineRunner {
		userService.registerUser("jim@bob.com", "pirate")
		userService.registerUser("joe@bob.com", "pirate")
		authorityService.createAuthority("USER")
		authorityService.createAuthority("ADMIN")
		userService.grantAuthorityToUser("jim@bob.com", "USER")
		userService.grantAuthorityToUser("joe@bob.com", "ADMIN")
	}

	@Bean
	fun passwordEncoder(): BCryptPasswordEncoder {
		return BCryptPasswordEncoder()
	}
}

fun main(args: Array<String>) {
	runApplication<Ent1Uke8ovingApplication>(*args)
}
