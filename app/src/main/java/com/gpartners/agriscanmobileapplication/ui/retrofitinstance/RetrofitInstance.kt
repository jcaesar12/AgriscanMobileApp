package com.gpartners.agriscanmobileapplication.ui.retrofitinstance

import com.gpartners.agriscanmobileapplication.ui.retrofitinterfaces.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.238:8000/") // FastAPI base URL
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Declare all APIs here
    val chatbotApi: ChatbotApi by lazy {
        retrofit.create(ChatbotApi::class.java)
    }

    val farmerApi: FarmerRegistrationApi by lazy {
        retrofit.create(FarmerRegistrationApi::class.java)
    }

    val scannerApi: ScannerApi by lazy {
        retrofit.create(ScannerApi::class.java)
    }

    val farmerLoginApi: FarmerLoginApi by lazy {
        retrofit.create(FarmerLoginApi::class.java)
    }

    val profileApi: ProfileApi by lazy {
        retrofit.create(ProfileApi::class.java)
    }

    val carbonTrackerApi: CarbonTrackerApi by lazy {
        retrofit.create(CarbonTrackerApi::class.java)
    }
}