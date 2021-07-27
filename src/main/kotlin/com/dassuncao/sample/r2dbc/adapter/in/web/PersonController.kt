package com.dassuncao.sample.r2dbc.adapter.`in`.web

import com.dassuncao.sample.r2dbc.core.port.`in`.SavePersonUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reactive")
class PersonController(
    private val savePersonUseCase: SavePersonUseCase
) {

    @PostMapping("/create")
    fun createPerson(@RequestBody personRequest: List<PersonRequest>) =
        savePersonUseCase
            .saveAll(listOf(personRequest.first().toPerson()))
}
