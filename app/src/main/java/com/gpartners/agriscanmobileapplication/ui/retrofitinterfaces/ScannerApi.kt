package com.gpartners.agriscanmobileapplication.ui.retrofitinterfaces

import com.gpartners.agriscanmobileapplication.ui.disease_scanner.ImageScanCreateDTO
import com.gpartners.agriscanmobileapplication.ui.disease_scanner.ImageScanDTO
import com.gpartners.agriscanmobileapplication.ui.disease_scanner.RecentScansDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ScannerApi {

    // Upload a new scan
    @POST("/scanner/upload")
    fun uploadScan(@Body scan: ImageScanCreateDTO): Call<ImageScanDTO>

    // Get recent scans for a farmer
    @GET("/scanner/recent/{farmer_id}")
    fun getRecentScans(@Path("farmer_id") farmerId: Int): Call<RecentScansDTO>
}
