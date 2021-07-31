package com.prj.trainingapp.ui.hourly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HourlyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is hourly Fragment"
    }
    val text: LiveData<String> = _text
}