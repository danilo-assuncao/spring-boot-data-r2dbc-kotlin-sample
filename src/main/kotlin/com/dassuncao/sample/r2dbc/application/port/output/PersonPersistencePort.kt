package com.dassuncao.sample.r2dbc.application.port.output

import com.dassuncao.sample.r2dbc.adapter.output.persistence.PersonEntity
import reactor.core.publisher.Flux

interface PersonPersistencePort {

    fun findPersonByName(personEntity: PersonEntity): Flux<PersonEntity>

    fun saveAll(personEntities: List<PersonEntity>): Flux<PersonEntity>
}
