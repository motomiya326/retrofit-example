package net.laurahouse.retrofit_example

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import net.laurahouse.retrofit_example.data.api.ApiClientManager
import net.laurahouse.retrofit_example.data.api.response.ZipResponse
import net.laurahouse.retrofit_example.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main) }
    private val compositeSubscription = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.buttonSubmit.setOnClickListener { _ ->
            val zipcode = binding.editText.text.toString()
            if (!TextUtils.isEmpty(zipcode)) {
                compositeSubscription.clear()
                compositeSubscription.add(
                        ApiClientManager.apiClient.getZipCode(zipcode)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnNext {
                                    Log.d(TAG, "rx response=$it")
                                    binding.textViewResponse.text = Gson().toJson(it)
                                }
                                .doOnError {
                                }
                                .doOnCompleted {
                                }
                                .subscribe())

                // 【おまけ】 getZipCodeCall()の場合
                ApiClientManager.apiClient.getZipCodeCall(zipcode).enqueue(object: Callback<ZipResponse> {
                    override fun onResponse(call: Call<ZipResponse>, response: retrofit2.Response<ZipResponse>) {
                        if (response.isSuccessful) {
                            Log.d(TAG, "call response=${response.body()}")
                        }
                    }
                    override fun onFailure(call: Call<ZipResponse>, t: Throwable) {

                    }
                })

            }
        }
    }

    override fun onDestroy() {
        compositeSubscription.clear()
        super.onDestroy()
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName!!
    }
}
