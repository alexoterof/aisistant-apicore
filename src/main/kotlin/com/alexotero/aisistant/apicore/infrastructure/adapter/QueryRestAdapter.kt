package com.alexotero.aisistant.apicore.infrastructure.adapter

import com.alexotero.aisistant.apicore.application.UserAsksChatGPTUseCase
import com.alexotero.aisistant.apicore.domain.chat.*
import com.alexotero.aisistant.apicore.infrastructure.controllers.dto.QueryDTO
import org.springframework.stereotype.Component

@Component
class QueryRestAdapter(
    private val userAsksChatGPTUseCase: UserAsksChatGPTUseCase
) {

    fun query(queryDTO: QueryDTO): Message{
        val interaction = map(queryDTO)
        return userAsksChatGPTUseCase.execute(interaction)
    }

    private fun map(queryDTO: QueryDTO): UserInteractionRequest =
        UserInteractionRequest(
            interactionUUID = InteractionUUID(queryDTO.uuid),
            message = Message(Role.USER, Content(queryDTO.query))
        )
}