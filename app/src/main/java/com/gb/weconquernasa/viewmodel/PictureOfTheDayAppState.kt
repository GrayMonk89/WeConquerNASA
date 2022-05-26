package com.gb.weconquernasa.viewmodel

import com.gb.weconquernasa.repository.dto.PictureOfTheDayResponseData

sealed class PictureOfTheDayAppState{
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData):PictureOfTheDayAppState()
    data class Loading(val progress:Int?):PictureOfTheDayAppState()
    data class Error(val error:Throwable):PictureOfTheDayAppState()
}
