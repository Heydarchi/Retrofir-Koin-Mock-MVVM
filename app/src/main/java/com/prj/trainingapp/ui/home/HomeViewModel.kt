package com.prj.trainingapp.ui.home


import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prj.trainingapp.R
import com.prj.trainingapp.api.RetroCurrentWeatherInterface
import com.prj.trainingapp.common.ResourceProvider
import com.prj.trainingapp.model.CurrentWeatherData
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _currentWeatherData = MutableLiveData<CurrentWeatherData>().apply {
    }

    val currentWeatherData: MutableLiveData<CurrentWeatherData> = _currentWeatherData

}