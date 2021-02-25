package com.harsh.databindingwithrecyclerview.data

import com.google.gson.JsonObject
import com.harsh.databindingwithrecyclerview.model.Article
import io.reactivex.Observable
import retrofit2.http.*

object APICONSTANT {
    const val HEADLINES = "top-headlines"
}

interface ApiInterface {

    //    https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=YOUR_API_KEY
    @GET(APICONSTANT.HEADLINES)
    suspend fun getHeadLines(
        @Query("sources") source: String,
        @Query("apiKey") apiKey: String
    ): ApiResponse<ArrayList<Article>>

    @FormUrlEncoded
    @POST(APICONSTANT.HEADLINES)
    fun getMethodName(@Field("field") field: String?):
            Observable<ApiResponse<ArrayList<JsonObject>>>
}