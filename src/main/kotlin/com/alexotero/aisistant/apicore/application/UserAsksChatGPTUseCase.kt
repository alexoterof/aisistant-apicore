package com.alexotero.aisistant.apicore.application

import com.alexotero.aisistant.apicore.domain.Model
import com.alexotero.aisistant.apicore.domain.chat.*
import com.alexotero.aisistant.apicore.infrastructure.repository.ChatGPTRepository
import com.alexotero.aisistant.apicore.infrastructure.repository.MessageRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class UserAsksChatGPTUseCase(
    private val chatGPTRepository: ChatGPTRepository,
    private val messageRepository: MessageRepository
) {
    @Value("\${chat.gpt.terminal.prompt}")
    lateinit var terminalPrompt: String

    fun execute(userInteractionRequest: UserInteractionRequest): Message{
        val messages = messagesListRetrievingPrevious(userInteractionRequest)
        log(MessageSaveRequest(userInteractionRequest.interactionUUID, messages.last()))

        return getAndLogResponse(userInteractionRequest.interactionUUID, messages)
    }

    private fun getAndLogResponse(interactionUUID: InteractionUUID, messages: List<Message>): Message{
        val response:Message = chatGPTRepository.getChatResponse(
            Interaction(
                uuid = interactionUUID,
                model = Model.GPT3DOT5TURBO,
                messages = messages
            )
        )

        log(MessageSaveRequest(interactionUUID, response))
        return response
    }

    private fun log(messageSaveRequest: MessageSaveRequest){
        messageRepository.saveMessage(messageSaveRequest)
    }

    private fun messagesListRetrievingPrevious(userInteractionRequest: UserInteractionRequest): MutableList<Message>{
        val messages: MutableList<Message>  = mutableListOf(getSystemMessage())
        messages.addAll(messageRepository.getMessagesByInteraction(userInteractionRequest.interactionUUID))
        messages.add(userInteractionRequest.message)
        return messages
    }

    private fun getSystemMessage(): Message =
        Message(
            Role.SYSTEM,
            content = Content(terminalPrompt)
        )
}