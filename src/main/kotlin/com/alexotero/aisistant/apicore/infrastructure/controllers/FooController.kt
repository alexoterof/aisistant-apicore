package com.alexotero.aisistant.apicore.infrastructure.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class xFooController {

    @GetMapping("/health")
    fun health(): ResponseEntity<Map<String,Any>> {
        return ResponseEntity.status(HttpStatus.OK).body(mapOf("message" to "ok!"))
    }
}