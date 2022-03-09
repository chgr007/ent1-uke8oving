package com.groyp.ent1uke8oving.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    val id: Long? = null,

    @Column(name = "user_email")
    val email: String,

    @Column(name = "user_password")
    val password: String,
    
    @Column(name = "user_enabled")
    val enabled: Boolean = true,

    @Column(name = "user_created")
    val created: LocalDateTime = LocalDateTime.now()

) {
    @ManyToMany(fetch = FetchType.EAGER)
    val authorities: MutableList<AuthorityEntity> = mutableListOf()
}
