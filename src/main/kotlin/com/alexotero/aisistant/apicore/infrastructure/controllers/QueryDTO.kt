package com.alexotero.aisistant.apicore.infrastructure.controllers

import java.util.UUID

data class QueryDTO (
    val uuid: UUID,
    val query: String
)