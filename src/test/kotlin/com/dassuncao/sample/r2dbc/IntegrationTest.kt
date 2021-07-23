package com.dassuncao.sample.r2dbc

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.r2dbc.core.DatabaseClient
import reactor.core.publisher.Hooks
import reactor.kotlin.test.test
import reactor.test.StepVerifier

@SpringBootTest
@Profile("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class IntegrationTest {

    @Autowired
    lateinit var database: DatabaseClient

    @BeforeEach
    fun setUp() {
        Hooks.onOperatorDebug()
        val statements: List<String> = listOf(
                "DROP TABLE IF EXISTS person;",
                "CREATE TABLE PERSON(id SERIAL PRIMARY KEY, name VARCHAR(255), last_name VARCHAR(255), age INT);"
        )

        statements.forEach {
            database.sql(it)
                    .fetch()
                    .rowsUpdated()
                    .test()
                    .expectNextCount(1)
                    .verifyComplete()
        }
    }
}
