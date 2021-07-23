package com.dassuncao.sample.r2dbc.core.service

import com.dassuncao.sample.r2dbc.adapter.out.persistence.toPerson
import com.dassuncao.sample.r2dbc.adapter.out.persistence.toPersonEntity
import com.dassuncao.sample.r2dbc.core.domain.Person
import com.dassuncao.sample.r2dbc.core.port.`in`.FindPersonUseCase
import com.dassuncao.sample.r2dbc.core.port.out.PersonPersistencePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FindPersonService(
    @Autowired private val personPersistencePort: PersonPersistencePort
) : FindPersonUseCase {

    override fun findPersonByName(person: Person) =
        personPersistencePort
            .findPersonByName(person.toPersonEntity())
            .map { it.toPerson() }

    override fun saveAll(people: List<Person>) =
        personPersistencePort
            .saveAll(people.map { it.toPersonEntity() })
            .map { it.toPerson() }
}
