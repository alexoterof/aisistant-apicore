package com.alexotero.aisistant.apicore.domain.chat

import com.alexotero.aisistant.apicore.domain.Model

data class Interaction(
    val uuid: InteractionUUID,
    val model: Model,
    val messages: List<Message>
)
