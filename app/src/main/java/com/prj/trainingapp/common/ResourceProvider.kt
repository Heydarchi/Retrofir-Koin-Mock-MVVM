package com.prj.trainingapp.common

import android.content.Context

class ResourceProvider(val context : Context) {

    fun getContextProvider() : Context = context

    fun getStringResource( id : Int) : String = context.getString(id)
}