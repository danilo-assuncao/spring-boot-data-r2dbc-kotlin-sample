package com.dassuncao.sample.r2dbc.adapter.`in`.web

import com.dassuncao.sample.r2dbc.core.domain.Person

fun PersonRequest.toPerson() = Person(
    name = name,
    lastName = lastName,
    age = age
)
