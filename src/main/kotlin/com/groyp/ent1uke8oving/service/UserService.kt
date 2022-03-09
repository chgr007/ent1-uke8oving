package com.groyp.ent1uke8oving.service

import com.groyp.ent1uke8oving.model.AuthorityEntity
import com.groyp.ent1uke8oving.model.UserEntity
import com.groyp.ent1uke8oving.repo.AuthorityRepo
import com.groyp.ent1uke8oving.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    @Autowired private val userRepo: UserRepo,
    @Autowired private val authorityService: AuthorityService,
    @Autowired private val passwordEncoder: BCryptPasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(email: String?): UserDetails {
        val user = userRepo.findByEmail(email)
        return User(
            user.email,
            user.password,
            user.authorities.map { SimpleGrantedAuthority(it.name) }
        )
    }

    fun getUsers(): List<UserEntity> {
        return userRepo.findAll()
    }

    fun registerUser(email: String, password: String): UserEntity {
        val newUser = UserEntity(email = email, password = passwordEncoder.encode(password))
        return userRepo.save(newUser)
    }

    fun grantAuthorityToUser(email: String, authorityName: String){
        val user = userRepo.findByEmail(email)
        val authority = authorityService.getAuthorityByName(authorityName)
        authority?.let {user.authorities.add(authority)}
        userRepo.save(user)
    }
}