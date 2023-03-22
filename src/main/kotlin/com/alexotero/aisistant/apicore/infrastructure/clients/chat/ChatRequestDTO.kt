package com.alexotero.aisistant.apicore.infrastructure.clients.chat

data class ChatRequestDTO(
    val model: String,
    val messages: List<ChatMessageDTO>
)
