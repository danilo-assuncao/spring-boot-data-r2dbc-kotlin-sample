package com.dassuncao.sample.r2dbc.core.service

import com.dassuncao.sample.r2dbc.IntegrationTest
import com.dassuncao.sample.r2dbc.core.domain.Person
import com.dassuncao.sample.r2dbc.core.port.`in`.FindPersonUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import reactor.kotlin.test.test

class FindPersonServiceTest(
    @Autowired private val findPersonUseCase: FindPersonUseCase
) : IntegrationTest() {

    @Test
    fun `Should save and find a person object using nextSequence`() {

        // Arrange
        val name = "Mayre"

        val people = listOf(
            Person(
                id = null,
                name = name,
                lastName = System.currentTimeMillis().toString(),
                age = 12
            ),
            Person(
                id = null,
                name = name,
                lastName = System.currentTimeMillis().toString(),
                age = 13
            )
        )

        // Act
        insertPerson(people)

        val expectedListPerson = listOf(
            Person(
                id = 1,
                name = name,
                lastName = people[0].lastName,
                age = 17
            ),
            Person(
                id = 2,
                name = name,
                lastName = people[1].lastName,
                age = 13
            )
        )
        val nameToFind = Person(
            id = 1,
            name = name,
            lastName = System.currentTimeMillis().toString(),
            age = 13
        )

        // Assert
        findPersonUseCase.findPersonByName(nameToFind)
            .log("Integration Test")
            .test()
            .expectNextSequence(expectedListPerson)
            .verifyComplete()
    }

    @Test
    fun `Should save and find a person object using assertNext`() {

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

        // Act
        insertPerson(listOf(person1, person2))

        val nameToFind = Person(
            id = 1,
            name = name,
            lastName = System.currentTimeMillis().toString(),
            age = 13
        )

        // Assert
        findPersonUseCase.findPersonByName(nameToFind)
            .log("Integration Test")
            .test()
            .assertNext {
                assertThat(it)
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(person1)
                assertThat(it.id)
                    .isNotNull
                    .isGreaterThan(0)
            }
            .assertNext {
                assertThat(it)
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(person2)
                assertThat(it.id)
                    .isNotNull
                    .isGreaterThan(1)
            }
            .verifyComplete()
    }

    private fun insertPerson(person: List<Person>) {
        findPersonUseCase.saveAll(person)
            .log("SaveAll")
            .test()
            .expectNextCount(2)
            .verifyComplete()
    }
}
