fun main() {

}


object ChatService {
    var chats = mutableListOf<Chat>()
    var id = 0

    fun sendMessage(user1Id: Int, user2Id: Int, text: String) {
        var message = Message(1, user1Id, text)
        val newChat = chats.filter { chat ->
            chat.users.containsAll(listOf(user1Id, user2Id))
        }.firstOrNull().let { chat ->
            chat?.copy(messages = chat.messages + message)
        } ?: Chat(
            id+1, listOf(user1Id, user1Id), listOf(message)
        )
        chats.removeIf { newChat.id == it.id }
        chats.add(newChat)
    }

    fun getChats(userId: Int) =
        chats.filter { chat: Chat ->

            chat.users.contains(userId)
        }
            .map { chat ->
                chat.messages
                    .map { message ->
                        message.isRead = true
                    }
            }


    fun readChat(chatId: Int): Boolean {
        chats.map { chat ->
            if (chat.id == chatId) chat.messages
                .map {
                    it.isRead = true
                    return true

                }
        }

        return false
    }

    fun getUnreadChatsCount(userId: Int) =
        chats.count { chat ->
            chat.users.contains(userId)
        }


    fun deleteChat(chatId: Int) = chats
        .filter { chat -> chat.id == chatId }
        .map { chat ->
            chat.copy(
                id = 0,
                users = emptyList(),
                messages = emptyList()
            )
        }

    fun editMessage(chatId: Int, messageId: Int, text: String) = chats.filter { chat ->
        chat.id == chatId
    }.map { chat ->
        chat.messages
            .filter { message -> message.id == messageId }
            .map { message -> message.message = text}


    }

    fun deleteMessage(chatId: Int, messageId: Int) :Boolean {
        chats.map { chat -> chat.messages.size }
        chats.filter { chat -> chat.id == chatId }.map { chat ->
            chat.messages
                .map { message ->
                    if (message.id == messageId) message.message = null
                }.firstOrNull()?.let {
                    return true
                }
        }
        return false
    }

    fun clear() {
        chats.map { chat ->
            chat.copy(
                id = 0,
                users = emptyList(),
                messages = emptyList()
            )

        }
    }
}
