package com.gb.weconquernasa.repository

import com.gb.weconquernasa.repository.dto.PictureOfTheDayResponseData
import com.gb.weconquernasa.utils.NASA_APOD_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET(NASA_APOD_ENDPOINT)
    fun getPictureOfTheDay(
        @Query("api_key") apyKey: String,
        @Query("date") date: String
    ): Call<PictureOfTheDayResponseData>
}