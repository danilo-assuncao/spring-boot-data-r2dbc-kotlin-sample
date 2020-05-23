package com.dassuncao.sample.r2dbc.application.port.input

import com.dassuncao.sample.r2dbc.application.domain.Person
import reactor.core.publisher.Flux

interface FindPersonUseCase {

    fun findPersonByName(person: Person): Flux<Person>

    fun saveAll(people: List<Person>): Flux<Person>
}
