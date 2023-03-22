package com.alexotero.aisistant.apicore.infrastructure.controllers

import com.alexotero.aisistant.apicore.infrastructure.adapter.QueryRestAdapter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryController(
    private val queryRestAdapter: QueryRestAdapter
) {

    @PostMapping("/query")
    fun query(@RequestBody queryDTO: QueryDTO): QueryResponseDTO? {
        val message = queryRestAdapter.query(queryDTO)
        return QueryResponseDTO(message.content.value)
    }
}