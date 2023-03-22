package com.alexotero.aisistant.apicore.domain.chat

data class UserInteractionRequest(
    val interactionUUID: InteractionUUID,
    val message: Message
)
