package com.gb.weconquernasa.repository

import com.gb.weconquernasa.BuildConfig
import com.gb.weconquernasa.MyApp
import com.gb.weconquernasa.repository.dto.PictureOfTheDayResponseData
import com.gb.weconquernasa.viewmodel.PictureOfTheDayViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayRetrofitImpl : PictureOfTheDayRepository {

    override fun getPictureOfTheDay(key: String, callback: PictureOfTheDayViewModel.Callback) {

        val pictureOfTheDayAPI = MyApp.getRetrofit()

        pictureOfTheDayAPI.getPictureOfTheDay(BuildConfig.NASA_API_KEY)
            .enqueue(object : Callback<PictureOfTheDayResponseData> {
                override fun onResponse(
                    call: Call<PictureOfTheDayResponseData>,
                    response: Response<PictureOfTheDayResponseData>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onResponse(it)
                        }
                    } else {
                        callback.onFailure(
                            "Ответ прише не верный. ${
                                response.errorBody().toString()
                            }"
                        )
                    }
                }

                override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
                    callback.onFailure(t.message.toString())
                }

            })
    }
}