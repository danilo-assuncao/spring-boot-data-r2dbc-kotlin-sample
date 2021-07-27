package com.dassuncao.sample.r2dbc.core.service

import com.dassuncao.sample.r2dbc.adapter.out.persistence.toPerson
import com.dassuncao.sample.r2dbc.adapter.out.persistence.toPersonEntity
import com.dassuncao.sample.r2dbc.core.domain.Person
import com.dassuncao.sample.r2dbc.core.port.`in`.SavePersonUseCase
import com.dassuncao.sample.r2dbc.core.port.out.PersonPersistencePort
import org.springframework.stereotype.Service

@Service
class SavePersonService(
    private val personPersistencePort: PersonPersistencePort
) : SavePersonUseCase {

    override fun saveAll(people: List<Person>) =
        personPersistencePort
            .saveAll(people.map { it.toPersonEntity() })
            .map { it.toPerson() }
}
