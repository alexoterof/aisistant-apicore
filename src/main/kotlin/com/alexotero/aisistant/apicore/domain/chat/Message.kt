package com.alexotero.aisistant.apicore.domain.chat

data class Message(
    val role: Role,
    val content: Content
)