package com.elitegadgetlabs.wingrid.composables

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.elitegadgetlabs.wingrid.composables.models.Charger
import com.elitegadgetlabs.wingrid.composables.models.ChargerModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.reflect.Type


@Composable
fun MapScreen(navController: NavController, mainViewModel: MainViewModel) {

    val client = OkHttpClient()

    fun httpRequest(url: String): Response? {
        val request = Request.Builder()
            .url(url)
            .build()
        var response: Response?
//        lateinit var result: String
        runBlocking(Dispatchers.IO) {
            response = client.newCall(request).execute()
//            result = response?.body?.string().toString()
        }
        return response
    }



    fun getChargerData(vm: MainViewModel): ChargerModel? {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: com.squareup.moshi.JsonAdapter<ChargerModel> =
            moshi.adapter(ChargerModel::class.java)

        var chargers: ChargerModel?
        runBlocking(Dispatchers.IO) {
            chargers = jsonAdapter.fromJson(
                httpRequest("http://ec2-3-96-163-40.ca-central-1.compute.amazonaws.com:8000//generate_new_chargers/${vm.numNewChargers}")?.body?.source()!!
            )
        }

        return chargers
    }


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(1.35, 103.87), 10f)
    }

    val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(navController.context as Activity)

    fusedLocationProviderClient.lastLocation.addOnSuccessListener(navController.context as Activity) { location ->
        cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(location.latitude, location.longitude), 10f)
    }



    GoogleMap(

        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {

        if (ActivityCompat.checkSelfPermission(
                navController.context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                navController.context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                navController.context as Activity,
                arrayOf(
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), 1
            )
            return@GoogleMap
        }

        Log.d("compose", getChargerData(mainViewModel).toString())

        for (charger in getChargerData(mainViewModel)?.chargers!!){
            Marker(
                position = LatLng(charger.latitude, charger.longitude),
                title = charger.name,
            )

        }

    }

}