package com.dassuncao.sample.r2dbc.adapter.out.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("person")
data class PersonEntity(

        @Id
        val id: Long? = null,

        val name: String,

        val lastName: String,

        val age: Int
)
