package com.dassuncao.sample.r2dbc

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.data.r2dbc.core.DatabaseClient
import reactor.core.publisher.Hooks
import reactor.test.StepVerifier

@SpringBootTest
@Profile("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractTest {

    @Autowired
    lateinit var database: DatabaseClient

    @BeforeAll
    fun setUp() {
        Hooks.onOperatorDebug()
        val statements: List<String> = listOf(
                "DROP TABLE IF EXISTS person;",
                "CREATE TABLE PERSON(id SERIAL PRIMARY KEY, name VARCHAR(255), last_name VARCHAR(255), age INT);"
        )

        statements.forEach {
            database.execute(it)
                    .fetch()
                    .rowsUpdated()
                    .`as`<StepVerifier.FirstStep<Int>> { publisher -> StepVerifier.create(publisher) }
                    .expectNextCount(1)
                    .verifyComplete()
        }
    }
}
