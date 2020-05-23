package com.dassuncao.sample.r2dbc

import com.dassuncao.sample.r2dbc.application.domain.Person
import com.dassuncao.sample.r2dbc.application.port.input.FindPersonUseCase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.test.StepVerifier

class R2dbcSampleApplicationTest(
        @Autowired private val findPersonUseCase: FindPersonUseCase
) : AbstractTest() {

    @Test
    fun `test of creating and searching data in the database using the reactive driver`() {

        // Arrange
        val name = "Mayre"

        val person1 = Person(
                id = null,
                name = name,
                lastName = System.currentTimeMillis().toString(),
                age = 12
        )

        val person2 = Person(
                id = null,
                name = name,
                lastName = System.currentTimeMillis().toString(),
                age = 13
        )

        insertPerson(
                person1,
                person2
        )

        val nameToFind = Person(
                id = System.currentTimeMillis(),
                name = name,
                lastName = System.currentTimeMillis().toString(),
                age = 13
        )

        // Act & Assert
        findPersonUseCase.findPersonByName(nameToFind)
                .`as`<StepVerifier.FirstStep<Person>> { publisher -> StepVerifier.create(publisher) }
                .expectNextCount(2)
                .verifyComplete()
    }

    private fun insertPerson(vararg person: Person) {
        findPersonUseCase.saveAll(person.toList())
                .`as`<StepVerifier.FirstStep<Person>> { publisher -> StepVerifier.create(publisher) }
                .expectNextCount(2)
                .verifyComplete()
    }
}
