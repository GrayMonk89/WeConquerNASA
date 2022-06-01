package com.gb.weconquernasa.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.weconquernasa.BuildConfig
import com.gb.weconquernasa.repository.PictureOfTheDayRepository
import com.gb.weconquernasa.repository.PictureOfTheDayRetrofitImpl
import com.gb.weconquernasa.repository.dto.PictureOfTheDayResponseData


class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayAppState> = MutableLiveData()
) : ViewModel() {

    private val pictureOfTheDayRepository: PictureOfTheDayRepository = PictureOfTheDayRetrofitImpl()

    fun getLiveData() = liveData

    fun getPicture(date: String) {
        pictureOfTheDayRepository.getPictureOfTheDay(
            BuildConfig.NASA_API_KEY,
            date,
            object : Callback {
                override fun onResponse(picture: PictureOfTheDayResponseData) {
                    liveData.postValue(PictureOfTheDayAppState.Success(picture))
                }

                override fun onFailure(errorMessage: String) {
                    liveData.postValue(PictureOfTheDayAppState.Error(Throwable(errorMessage)))
                }

            })
    }


    interface Callback {
        fun onResponse(picture: PictureOfTheDayResponseData)
        fun onFailure(errorMessage: String)
    }
}