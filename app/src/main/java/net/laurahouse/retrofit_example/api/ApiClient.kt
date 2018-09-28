package net.laurahouse.retrofit_example.data.api

import net.laurahouse.retrofit_example.data.api.response.ZipResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiClient {
    @GET("api/search")
    fun getZipCode(@Query("zipcode") zipcode: String): Observable<ZipResponse>


}