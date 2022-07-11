package com.gb.weconquernasa

import android.app.Application
import com.gb.weconquernasa.repository.PictureOfTheDayAPI
import com.gb.weconquernasa.utils.NASA_DOMAIN_PART
import com.google.android.material.color.DynamicColors
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        //DynamicColors.applyToActivitiesIfAvailable(this)
    }

    companion object {

        fun getRetrofit(): PictureOfTheDayAPI {

            val pictureOfTheDayRetrofit = Retrofit.Builder().apply {
                baseUrl(NASA_DOMAIN_PART)
                addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
            }.build().create(PictureOfTheDayAPI::class.java)

            return pictureOfTheDayRetrofit
        }
    }
}