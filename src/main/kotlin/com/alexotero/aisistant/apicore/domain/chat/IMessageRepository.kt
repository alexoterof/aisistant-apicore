package com.alexotero.aisistant.apicore.domain.chat

interface IMessageRepository {
    fun getMessagesByInteraction(interactionUUID: InteractionUUID): List<Message>
    fun saveMessage(messageSaveRequest: MessageSaveRequest)
}