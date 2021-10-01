
package com.example.marvelapi.repos


import com.example.marvelapi.application.MarvelApiService
import javax.inject.Inject

class getChractersRepo  @Inject constructor(var marvelApiService: MarvelApiService) {

    fun getCharcters()  = marvelApiService.getCharcters()

}