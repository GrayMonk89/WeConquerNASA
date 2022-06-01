package com.gb.weconquernasa.repository

import com.gb.weconquernasa.viewmodel.PictureOfTheDayViewModel

interface PictureOfTheDayRepository {
    fun getPictureOfTheDay(key: String, date: String,callback: PictureOfTheDayViewModel.Callback)
}