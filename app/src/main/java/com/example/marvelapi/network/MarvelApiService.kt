package com.example.marvelapi.application



import com.example.marvelapi.application.MarvelApi.Companion.ApiKey
import com.example.marvelapi.application.MarvelApi.Companion.hash
import com.example.marvelapi.application.MarvelApi.Companion.ts
import com.example.marvelapi.models.CharactersResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelApiService {


    @GET("characters")
    fun getCharcters(@Query ("apikey") key : String = ApiKey,
                     @Query ("ts") ts : String = MarvelApi.ts,
                     @Query ("hash") hash : String = hash(),
    ) : Observable<CharactersResponse>




}