package com.gpartners.agriscanmobileapplication.ui.retrofitinterfaces

import com.gpartners.agriscanmobileapplication.ui.login_and_register.FarmerRegistrationDTO
import com.gpartners.agriscanmobileapplication.ui.login_and_register.FarmerResponseDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


// Retrofit interface for Farmer Registration APIs
interface FarmerRegistrationApi {

    // ---------------- REGISTER FARMER ----------------
    @POST("farmer/register")
    fun registerFarmer(
        @Body farmerData: FarmerRegistrationDTO
    ): Call<FarmerResponseDTO>
}
