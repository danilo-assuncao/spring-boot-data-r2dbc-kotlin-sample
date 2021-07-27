package com.dassuncao.sample.r2dbc.core.port.`in`

import com.dassuncao.sample.r2dbc.core.domain.Person
import reactor.core.publisher.Flux

interface SavePersonUseCase {

    fun saveAll(people: List<Person>): Flux<Person>
}
