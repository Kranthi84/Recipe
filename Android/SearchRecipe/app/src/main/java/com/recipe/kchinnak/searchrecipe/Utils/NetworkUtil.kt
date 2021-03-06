package com.recipe.kchinnak.searchrecipe.utils

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkUtil private constructor() {


    lateinit var okhttpBuilder: OkHttpClient.Builder
    lateinit var logInterceptor: HttpLoggingInterceptor
    var mRetrofit: Retrofit? = null


    private object Holder {
        val networkinstance = NetworkUtil()
    }

    companion object {
        val instance: NetworkUtil by lazy { Holder.networkinstance }
    }


    fun retrofitBuilder(url: String?): Retrofit? {

        okhttpBuilder = OkHttpClient.Builder()
        logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okhttpBuilder.addInterceptor(logInterceptor)
        okhttpBuilder.connectTimeout(1, TimeUnit.MINUTES)
        okhttpBuilder.readTimeout(1, TimeUnit.MINUTES)


        url.let {
            mRetrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okhttpBuilder.build())
                    .build()
        }

        return mRetrofit

    }


}