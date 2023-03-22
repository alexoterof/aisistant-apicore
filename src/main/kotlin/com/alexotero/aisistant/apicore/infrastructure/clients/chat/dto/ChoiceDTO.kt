package com.alexotero.aisistant.apicore.infrastructure.clients.chat.dto

data class ChoiceDTO (
    val message: ChatMessageDTO,
    val finish_reason: String,
    val index: Int
)
