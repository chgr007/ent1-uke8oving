package com.groyp.ent1uke8oving.repo

import com.groyp.ent1uke8oving.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo: JpaRepository<UserEntity, Long> {

    fun findByEmail(email: String?) : UserEntity
}