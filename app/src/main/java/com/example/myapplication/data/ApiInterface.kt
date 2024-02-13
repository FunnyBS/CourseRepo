package com.example.myapplication.data

import com.example.myapplication.course.MenuCoursera
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("littleLemonMenu.json")
    suspend fun getRandomFact(): Response<MenuCoursera>
}