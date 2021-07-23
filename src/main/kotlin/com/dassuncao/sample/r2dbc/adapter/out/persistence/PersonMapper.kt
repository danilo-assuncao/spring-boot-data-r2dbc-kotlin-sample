package com.dassuncao.sample.r2dbc.adapter.out.persistence

import com.dassuncao.sample.r2dbc.core.domain.Person

fun PersonEntity.toPerson() =
    Person(
        id = id,
        name = name,
        lastName = lastName,
        age = age
    )

fun Person.toPersonEntity() =
    PersonEntity(
        id = id,
        name = name,
        lastName = lastName,
        age = age
    )
