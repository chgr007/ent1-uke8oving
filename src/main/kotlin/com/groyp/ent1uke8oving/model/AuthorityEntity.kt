package com.groyp.ent1uke8oving.model

import javax.persistence.Entity
import javax.persistence.*

@Entity
class AuthorityEntity (

    @Id
    @GeneratedValue
    @Column(name="authority_id")
    val id: Long? = null,

    @Column(name="authority_name")
    val name: String?
)   {
    override fun toString(): String {
        return "AuthorityEntity(id=$id, name=$name)"
    }
}
