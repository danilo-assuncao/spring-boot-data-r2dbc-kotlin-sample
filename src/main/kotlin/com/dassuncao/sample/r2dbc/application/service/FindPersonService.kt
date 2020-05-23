package com.dassuncao.sample.r2dbc.application.service

import com.dassuncao.sample.r2dbc.application.domain.Person
import com.dassuncao.sample.r2dbc.application.mapper.PersonMapper
import com.dassuncao.sample.r2dbc.application.port.input.FindPersonUseCase
import com.dassuncao.sample.r2dbc.application.port.output.PersonPersistencePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class FindPersonService(
        @Autowired private val personPersistencePort: PersonPersistencePort,
        @Autowired private val personMapper: PersonMapper
) : FindPersonUseCase {

    override fun findPersonByName(person: Person): Flux<Person> {
        return personPersistencePort
                .findPersonByName(personMapper.toPersonEntity(person))
                .map { personMapper.toPerson(it) }
    }

    override fun saveAll(people: List<Person>): Flux<Person> {
        return personPersistencePort
                .saveAll(people.map { personMapper.toPersonEntity(it) })
                .map { personMapper.toPerson(it) }
    }
}
