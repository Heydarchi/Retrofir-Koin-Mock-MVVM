package com.prj.trainingapp.modules

import com.prj.trainingapp.api.RetroCurrentWeatherApi
import org.koin.dsl.module

val retroModules = module{
    single{ RetroCurrentWeatherApi().initialize()}
}