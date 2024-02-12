package com.example.myapplication.data

import retrofit2.Response
import retrofit2.http.GET

interface MenuApi {

    @GET("/Salads")
    suspend fun getDishes (): Response<Salads>
}