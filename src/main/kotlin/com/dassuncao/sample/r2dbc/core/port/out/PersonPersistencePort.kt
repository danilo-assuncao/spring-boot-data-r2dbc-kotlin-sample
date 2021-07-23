package com.dassuncao.sample.r2dbc.core.port.out

import com.dassuncao.sample.r2dbc.adapter.out.persistence.PersonEntity
import reactor.core.publisher.Flux

interface PersonPersistencePort {

    fun findPersonByName(personEntity: PersonEntity): Flux<PersonEntity>

    fun saveAll(personEntities: List<PersonEntity>): Flux<PersonEntity>
}
