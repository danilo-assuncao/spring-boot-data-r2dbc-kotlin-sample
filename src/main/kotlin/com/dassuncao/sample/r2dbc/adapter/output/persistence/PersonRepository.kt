package com.dassuncao.sample.r2dbc.adapter.output.persistence

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface PersonRepository : ReactiveCrudRepository<PersonEntity, Long> {

    @Query("SELECT * FROM person WHERE name = :name")
    fun findByName(name: String): Flux<PersonEntity>
}
