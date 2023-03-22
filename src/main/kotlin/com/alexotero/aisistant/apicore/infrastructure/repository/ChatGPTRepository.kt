package com.alexotero.aisistant.apicore.infrastructure.repository

import com.alexotero.aisistant.apicore.domain.chat.Content
import com.alexotero.aisistant.apicore.domain.chat.Interaction
import com.alexotero.aisistant.apicore.domain.chat.Message
import com.alexotero.aisistant.apicore.domain.chat.Role
import com.alexotero.aisistant.apicore.infrastructure.clients.chat.ChatGPTClient
import com.alexotero.aisistant.apicore.infrastructure.clients.chat.dto.ChatMessageDTO
import com.alexotero.aisistant.apicore.infrastructure.clients.chat.dto.ChatRequestDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class ChatGPTRepository(
    private val chatGPTClient: ChatGPTClient,
    private val messageRepository: MessageRepository
) {
    @Value("\${chat.gpt.key}")
    lateinit var gptKey: String

    fun getChatResponse(interaction: Interaction): Message{
        val lastChoiceMessage = chatGPTClient.getChatResponse(
            gptKey,
            map(interaction)
        ).choices.first().message

        return Message(Role.ASSISTANT, Content(lastChoiceMessage.content))
    }

    private fun map(interaction: Interaction): ChatRequestDTO =
        ChatRequestDTO(
            model = interaction.model.modelCode,
            messages = interaction.messages.map {
                ChatMessageDTO(it.role.roleCode, it.content.value)
            }
        )
}