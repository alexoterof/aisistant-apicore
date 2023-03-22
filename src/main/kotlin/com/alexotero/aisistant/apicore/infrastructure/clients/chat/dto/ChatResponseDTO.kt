package com.alexotero.aisistant.apicore.infrastructure.clients.chat.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatResponseDTO(
    val id: String,
    @JsonProperty("object")
    val interactionType: String,
    val created: Long,
    val model: String,
    val usage: Map<String, Int>,
    val choices: List<ChoiceDTO>
)
