package com.navarayan.weatherexpectancy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.navarayan.weatherexpectancy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()
    private var latitude: Double? = null
    private var longitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (locationService()) {
            getLocation()
            binding.buttonStart.setOnClickListener {
                if ((latitude != null) && (longitude != null)) {
                    viewModel.onStartClicked(latitude!!, longitude!!, "metric")
                    viewModel.liveDataForWeatherResult.observe(
                        this,
                        {
                            binding.mainText.text =
                                getString(R.string.temp, it.main.temp.toString(), it.name)
                        })
                } else {
                    Toast.makeText(this, R.string.gps_unavailable, Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(this, R.string.gps_offline,Toast.LENGTH_LONG).show()
        }
    }

    private fun locationService(): Boolean {
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return LocationManagerCompat.isLocationEnabled(locationManager)
    }

    private fun getLocation() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100
                )
                return
            }
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 100
                )
                return
            }
            else -> {
                val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

                val locationListener = LocationListener { location ->
                    latitude = location.latitude
                    longitude = location.longitude
                }

                locationManager!!.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    0L,
                    0f,
                    locationListener
                )
            }
        }
    }
}