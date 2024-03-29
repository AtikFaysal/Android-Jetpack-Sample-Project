package com.example.androidjetpacksampleproject.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Atik Faysal(Android Developer)
 * @Email mdatikfaysal@gmail.com
 * Created 11/9/2021 at 11:56 AM
 */
@Singleton
class NetworkHelper @Inject constructor(@ApplicationContext private val mContext : Context)
{
    /**
     * ...Check internet connection status
     * ...Is device connected with internet
     * ...Check android os version
     * @return if device connected with internet return true otherwise false
     */
    fun isNetworkConnected() : Boolean
    {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M)//if os version is getter than marshmallow
        {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
        }else
        {
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null) return networkInfo.state == NetworkInfo.State.CONNECTED

            return false
        }

        return false
    }
}