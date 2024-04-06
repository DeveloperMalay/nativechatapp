package com.example.nativechatapp.data


data class UserData(
    var userId: String? = null,
    var name: String? = null,
    var number: String? = null,
    var imageUrl: String? = null,
) {
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "number" to number,
        "imageUrl" to imageUrl
    )
}

data class ChatsData(
    val chatId: String? = "",
    val user1: ChatUser = ChatUser(),
    val user2: ChatUser = ChatUser(),
)

data class ChatUser(
    val userId: String? = "",
    val name: String? = "",
    val imageUrl: String? = "",
    val number: String? = ""
)
