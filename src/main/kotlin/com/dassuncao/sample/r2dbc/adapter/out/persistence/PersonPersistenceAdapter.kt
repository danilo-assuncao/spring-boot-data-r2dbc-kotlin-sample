package com.dassuncao.sample.r2dbc.adapter.out.persistence

import com.dassuncao.sample.r2dbc.core.port.out.PersonPersistencePort
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class PersonPersistenceAdapter(
        private val personRepository: PersonRepository
) : PersonPersistencePort {

    override fun findPersonByName(personEntity: PersonEntity): Flux<PersonEntity> =
            personRepository.findByName(personEntity.name)

    override fun saveAll(personEntities: List<PersonEntity>): Flux<PersonEntity> =
            personRepository.saveAll(personEntities)
}
