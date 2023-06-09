package com.alexotero.aisistant.apicore.infrastructure.clients.chat

import com.alexotero.aisistant.apicore.infrastructure.clients.chat.dto.ChatRequestDTO
import com.alexotero.aisistant.apicore.infrastructure.clients.chat.dto.ChatResponseDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(name = "chatGPTClient", url = "https://api.openai.com/v1")
interface ChatGPTClient {

    @PostMapping("/chat/completions")
    fun getChatResponse(@RequestHeader("Authorization") authorizationHeader: String,
                        chatRequestDTO: ChatRequestDTO
    ): ChatResponseDTO

}