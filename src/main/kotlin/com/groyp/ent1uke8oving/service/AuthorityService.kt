package com.groyp.ent1uke8oving.service

import com.groyp.ent1uke8oving.model.AuthorityEntity
import com.groyp.ent1uke8oving.repo.AuthorityRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorityService(
    @Autowired private val authorityRepo: AuthorityRepo
) {

    fun createAuthority(authorityName: String): AuthorityEntity {
        return authorityRepo.save(AuthorityEntity(name = authorityName))
    }

    fun getAuthorities(): List<AuthorityEntity> {
        return authorityRepo.findAll()
    }

    fun getAuthorityByName(authorityName: String): AuthorityEntity {
        return authorityRepo.findByName(authorityName)
    }
}
