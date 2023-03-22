package com.alexotero.aisistant.apicore.domain.chat

enum class Role(
    val roleCode: String
){
    SYSTEM("system"),
    USER("user"),
    ASSISTANT("assistant");

    companion object {
        fun from(roleCode: String): Role {
                return values().first { it.roleCode == roleCode }
        }
    }

}