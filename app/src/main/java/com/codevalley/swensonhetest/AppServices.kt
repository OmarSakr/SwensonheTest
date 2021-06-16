package com.codevalley.swensonhetest

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AppServices {

    @GET("latest?access_key=87386eda0f540f91e52560fa941d439e")
    fun test(): Call<TestModel>



}