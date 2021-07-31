package com.prj.trainingapp.ui.hourly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.prj.trainingapp.R

class HourlyFragment : Fragment() {

    private lateinit var hourlyViewModel: HourlyViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        hourlyViewModel =
                ViewModelProvider(this).get(HourlyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_hourly, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        hourlyViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}