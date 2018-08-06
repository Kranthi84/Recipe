package com.recipe.kchinnak.searchrecipe.Utils

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkUtil private constructor() {


    private object Holder {
        val networkinstance = NetworkUtil()
    }

    companion object {
        val instance: NetworkUtil by lazy { Holder.networkinstance }
    }


    fun retrofitBuilder(mContext: Context): Retrofit {

        return Retrofit.Builder()
                .baseUrl(ConfigUtil().getConfigValue(mContext, "base_url"))
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }


}