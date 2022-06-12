import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChatServiceTest {
    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
        ChatService.chats.clear()

    }

    @Test
    fun sendMessage() {
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        assertEquals(1, ChatService.chats.size)

    }

    /* @Test
     fun getChats() {
         val user1Id = 1
         val user2Id = 2
         val text = "1"
         ChatService.getChats(user1Id)
         ChatService.getChats(1)


     }*/
    @Test
    fun readChatTrue() {
        val user1Id = 1
        val user2Id = 2
        val text = "1"

        ChatService.sendMessage(user1Id, user2Id, text)
        ChatService.getChats(1)
        val result = ChatService.readChat(1)
        assertTrue(result)

    }

    @Test
    fun ReadChatFalse() {
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)

        val result = ChatService.readChat(44)
        assertFalse(result)
    }

    @Test
    fun getUnreadChatsCount() {
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        val result = ChatService.getUnreadChatsCount(1)
        assertEquals(1, result)
    }

    @Test
    fun getUnreadChatsCountFalse() {
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        val result = ChatService.getUnreadChatsCount(44)
        assertNotEquals(1, result)
    }

    @Test
    fun deleteChatComplete() {
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        val expected = mutableListOf(Chat(0, emptyList(), emptyList()))
        val actual = ChatService.deleteChat(1)
        assertEquals(expected, actual)
    }

    @Test
    fun deleteChatFalse() {
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)
        val expected = mutableListOf(Chat(0, emptyList(), emptyList()))
        val actual = ChatService.deleteChat(3)
        assertNotEquals(expected, actual)

    }

    @Test
    fun editMessageComplete() {


    }

    @Test
    fun deleteMessageCopmlete() {

    }

    @Test
    fun clearComplete() {
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)

        val expected = mutableListOf(Chat(0, emptyList(), emptyList()))
        val actual = ChatService.deleteChat(1)
        assertEquals(expected, actual)


    }

    @Test
    fun clearFalse() {
        val user1Id = 1
        val user2Id = 2
        val text = "1"
        ChatService.sendMessage(user1Id, user2Id, text)

        val expected = mutableListOf(Chat(0, emptyList(), emptyList()))
        val actual = ChatService.deleteChat(144)
        assertNotEquals(expected, actual)

    }


}