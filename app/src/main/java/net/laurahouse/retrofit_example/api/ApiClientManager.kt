package net.laurahouse.retrofit_example.data.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class ApiClientManager {
    companion object {
        private const val ENDPOINT = "http://zipcloud.ibsnet.co.jp/"
        private val TAG = ApiClientManager::class.simpleName

        val apiClient: ApiClient
            get() = Retrofit.Builder()
                    .client(getClient())
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
                    .create(ApiClient::class.java)

        private fun getClient(): OkHttpClient {
            return OkHttpClient
                    .Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
        }
    }


}