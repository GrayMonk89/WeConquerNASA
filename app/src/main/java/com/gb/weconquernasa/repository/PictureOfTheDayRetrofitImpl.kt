package com.gb.weconquernasa.repository

import com.gb.weconquernasa.utils.NASA_DOMAIN_PART
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PictureOfTheDayRetrofitImpl {
    private val nasaBaseUrl = NASA_DOMAIN_PART
    fun getRetrofit():PictureOfTheDayAPI {
        val pictureOfTheDayRetrofit = Retrofit.Builder()
            .baseUrl(nasaBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return pictureOfTheDayRetrofit.create(PictureOfTheDayAPI::class.java)
    }
}