package com.prj.trainingapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prj.trainingapp.R
import com.prj.trainingapp.api.RetroCurrentWeatherInterface
import com.prj.trainingapp.model.CurrentWeatherData
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val weatherService : RetroCurrentWeatherInterface by inject()

    private lateinit var edtCityName : EditText
    private lateinit var btnGetWeather : Button
    private lateinit var txtCurrent: TextView
    private lateinit var txtFeels: TextView
    private lateinit var txtMax: TextView
    private lateinit var txtMin: TextView
    private lateinit var txtHumidity: TextView
    private lateinit var txtPressure: TextView
    private lateinit var txtVisibility: TextView
    private lateinit var txtWind: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        edtCityName = root.findViewById(R.id.edtCityName)
        btnGetWeather = root.findViewById(R.id.btnGetWeather)
        txtCurrent = root.findViewById(R.id.txtCurrent)
        txtFeels = root.findViewById(R.id.txtFeels)
        txtMax = root.findViewById(R.id.txtMax)
        txtMin = root.findViewById(R.id.txtMin)
        txtHumidity = root.findViewById(R.id.txtHumidity)
        txtPressure = root.findViewById(R.id.txtPressure)
        txtVisibility = root.findViewById(R.id.txtVisibility)
        txtWind = root.findViewById(R.id.txtWind)

        homeViewModel.currentWeatherData.observe(viewLifecycleOwner, Observer {
            txtCurrent.text = it.main.temp.toString()
            txtFeels.text = it.main.feels_like.toString()
            txtMax.text = it.main.temp_max.toString()
            txtMin.text = it.main.temp_min.toString()
            txtHumidity.text = it.main.humidity.toString()+"%"
            txtPressure.text = it.main.pressure.toString()
            txtVisibility.text = when {
                it.visibility > 1000 -> (it.visibility/1000).toString()+" Km"
                else -> it.visibility.toString()+" m"
            }
            txtWind.text = it.wind.speed.toString()


        })

        btnGetWeather.setOnClickListener { onGetWeather() }

        return root
    }

    private fun onGetWeather()
    {
        val call = weatherService.getCurrentByCityName(edtCityName.text.toString(),
                getString(R.string.api_key))

        call.enqueue(object: Callback<CurrentWeatherData> {
            override fun onResponse(
                    call: Call<CurrentWeatherData>,
                    response: Response<CurrentWeatherData>
            ) {
                Log.v("MyLog response.code()", response.code().toString())

                if(response.code()==200) {
                    val weather = response.body()!!
                    Log.v("MyLog", response.toString())
                    Log.v("MyLog", "${weather!!.main.temp}")
                    homeViewModel.currentWeatherData.postValue(weather)
                }
            }

            override fun onFailure(call: Call<CurrentWeatherData>, t: Throwable) {
                Log.v("MyLog", "Failed !")
                t.message?.let { Log.v("MyLog", it) }

            }

        })
    }
}