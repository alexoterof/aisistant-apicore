package com.alexotero.aisistant.apicore.infrastructure.clients.chat

data class ChoiceDTO (
    val message: ChatMessageDTO,
    val finish_reason: String,
    val index: Int
)
