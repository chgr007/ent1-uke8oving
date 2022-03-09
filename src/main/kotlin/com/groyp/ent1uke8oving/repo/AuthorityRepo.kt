package com.groyp.ent1uke8oving.repo

import com.groyp.ent1uke8oving.model.AuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepo: JpaRepository<AuthorityEntity, Long> {
    fun findByName(name: String): AuthorityEntity
}