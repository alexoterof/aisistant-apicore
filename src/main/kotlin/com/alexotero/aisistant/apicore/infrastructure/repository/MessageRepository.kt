package com.alexotero.aisistant.apicore.infrastructure.repository

import com.alexotero.aisistant.apicore.domain.chat.*
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.Instant

@Repository
class MessageRepository(
    private val jdbcTemplate: NamedParameterJdbcTemplate
): IMessageRepository {

    override fun getMessagesByInteraction(interactionUUID: InteractionUUID): List<Message> {
        return jdbcTemplate.query(
        "select * " +
            "from MESSAGES " +
            "where interaction_uuid =:interactionUUID " +
            "order by creation_date ",
            mapOf(Pair("interactionUUID", interactionUUID.uuid))
        ) {rs, _ ->
            Message(
                role = Role.from(rs.getString("role")),
                content = Content(rs.getString("content")
                )
            )
        }
    }

    override fun saveMessage(messageSaveRequest: MessageSaveRequest) {
        val sql = "INSERT INTO messages (interaction_uuid, role, content, creation_date) " +
                "VALUES (:interaction_uuid, :role, :content, :creation_date)"
        val params = MapSqlParameterSource()
            .addValue("interaction_uuid", messageSaveRequest.uuid.uuid)
            .addValue("role", messageSaveRequest.message.role.roleCode)
            .addValue("content", messageSaveRequest.message.content.value)
            .addValue("creation_date", Timestamp(Instant.now().toEpochMilli()))

        jdbcTemplate.update(sql, params)
    }

}