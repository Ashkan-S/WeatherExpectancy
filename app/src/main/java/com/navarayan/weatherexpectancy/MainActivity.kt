package com.navarayan.weatherexpectancy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.navarayan.weatherexpectancy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()
    var latitude: Double? = null
    var longitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getLocation()

        binding.buttonStart.setOnClickListener {
            viewModel.onStartClicked("Tehran")
/*
            if((latitude != null) && (longitude != null)) {
                viewModel.onStartClicked(latitude!!, longitude!!)
*/
                viewModel.liveDataForWeatherResult.observe(
                    this,
                    {
                        binding.mainText.text = it.main.temp.toString() + " C " + it.name.toString()
                    })

        }
    }

    private fun getLocation() {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        val locationListener = LocationListener { location ->
            binding.currentCity.text =
                "latitude: " + location.latitude.toString() + "\nlongitude: " + location.longitude.toString()
            latitude = location.latitude
            longitude = location.longitude
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100
            )
            return
        }

        locationManager!!.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0L,
            0f,
            locationListener
        )
    }
}