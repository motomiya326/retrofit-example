package net.laurahouse.retrofit_example.data.api

import net.laurahouse.retrofit_example.data.api.response.ZipResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import rx.Observable

interface ApiClient {
    @Headers("Accept: application/json", "X-App-Platform: android1")
    @GET("api/search")
    fun getZipCode(@Query("zipcode") zipcode: String): Observable<ZipResponse>

    // 【おまけ】Rxを使わない場合
    @Headers("Accept: application/json", "X-App-Platform: android2")
    @GET("api/search")
    fun getZipCodeCall(@Query("zipcode") zipcode: String): Call<ZipResponse>

}