package com.dassuncao.sample.r2dbc.adapter.out.persistence

import com.dassuncao.sample.r2dbc.UnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.test.test

class PersonPersistenceAdapterTest : UnitTest() {

    private val personRepository = mockk<PersonRepository>()
    private val personPersistenceAdapter = PersonPersistenceAdapter(personRepository)

    @Test
    fun `Should save all person object with success`() {
        // Arrange

        val expectedPersonEntity = listOf(
            PersonEntity(
                id = null,
                name = "Andre",
                lastName = "Santos",
                age = 32
            ),
            PersonEntity(
                id = null,
                name = "Andre",
                lastName = "Pae",
                age = 31
            )
        )

        every { personRepository.saveAll(expectedPersonEntity) } returns expectedPersonEntity.toFlux()

        // Act
        val actual = personPersistenceAdapter.saveAll(expectedPersonEntity)

        // Assert
        actual
            .test()
            .expectNextCount(2)
            .verifyComplete()
    }

    @Test
    fun `Should findBy Name with success`() {
        // Arrange
        val name = "Testa"

        val expectedPersonEntity = listOf(
            PersonEntity(
                id = System.nanoTime(),
                name = name,
                lastName = "Santos",
                age = 32
            ),
            PersonEntity(
                id = System.nanoTime(),
                name = name,
                lastName = "Pae",
                age = 31
            )
        )

        every { personRepository.findByName(name) } returns expectedPersonEntity.toFlux()

        // Act
        val actual = personPersistenceAdapter.findPersonByName(expectedPersonEntity.first())

        // Assert
        actual
            .log("FindByName")
            .test()
            .expectNextCount(2)
            .verifyComplete()

        verify { personRepository.findByName(name) }
    }
}
