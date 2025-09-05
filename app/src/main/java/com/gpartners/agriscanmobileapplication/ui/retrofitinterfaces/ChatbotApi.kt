package com.gpartners.agriscanmobileapplication.ui.retrofitinterfaces

import retrofit2.Call
import retrofit2.http.*
import com.gpartners.agriscanmobileapplication.ui.mwanedu.ChatHistoryDTO
import com.gpartners.agriscanmobileapplication.ui.mwanedu.ChatMessageDTO

// Retrofit interface for Chatbot APIs
interface ChatbotApi {

    // ---------------- SEND MESSAGE ----------------
    @POST("chatbot/send")
    fun sendMessage(
        @Body chat: ChatMessageDTO
    ): Call<ChatMessageDTO>

    // ---------------- GET CHAT HISTORY ----------------
    @GET("chatbot/history/{farmer_id}")
    fun getChatHistory(
        @Path("farmer_id") farmerId: Int
    ): Call<ChatHistoryDTO>
}
