package com.itesm.covapp

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class activity_map : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var MAP: GoogleMap
    private lateinit var userLastLocation: Location
    private lateinit var userCurrentLocation : FusedLocationProviderClient

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MAP = googleMap
        MAP.uiSettings.isZoomGesturesEnabled = true
        MAP.uiSettings.isZoomControlsEnabled = true
        MAP.mapType = GoogleMap.MAP_TYPE_HYBRID
        userCurrentLocation = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
            return
        }
        MAP.isMyLocationEnabled = true
        userCurrentLocation.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                userLastLocation = location
                MAP.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 17f))
            }
        }
    }
}
