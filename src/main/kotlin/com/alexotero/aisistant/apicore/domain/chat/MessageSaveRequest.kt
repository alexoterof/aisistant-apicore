package com.alexotero.aisistant.apicore.domain.chat

data class MessageSaveRequest(
    val uuid: InteractionUUID,
    val message: Message
)
