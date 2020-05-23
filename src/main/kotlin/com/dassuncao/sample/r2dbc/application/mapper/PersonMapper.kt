package com.dassuncao.sample.r2dbc.application.mapper

import com.dassuncao.sample.r2dbc.adapter.output.persistence.PersonEntity
import com.dassuncao.sample.r2dbc.application.domain.Person
import org.springframework.stereotype.Component

@Component
class PersonMapper {

    fun toPerson(personEntity: PersonEntity) =
            Person(
                    id = personEntity.id,
                    name = personEntity.name,
                    lastName = personEntity.lastName,
                    age = personEntity.age
            )

    fun toPersonEntity(person: Person) =
            PersonEntity(
                    id = person.id,
                    name = person.name,
                    lastName = person.lastName,
                    age = person.age
            )
}
