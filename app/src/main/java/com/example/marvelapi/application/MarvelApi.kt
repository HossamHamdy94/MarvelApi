package com.example.marvelapi.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

@HiltAndroidApp

class MarvelApi : Application () {

    companion object
    {
        val ts =  Timestamp (System.currentTimeMillis()).time.toString()
        var ApiKey =  "d2711eb8d1259b348a4614f60cb6d4d2"
        var ApikeyPrivate = "df4241f57146a8df001aede192d24e7888ccf56a"

        fun hash ():String  {
            val input = "$ts$ApikeyPrivate$ApiKey"
            val md = MessageDigest.getInstance("MD5")
            return  BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }


}