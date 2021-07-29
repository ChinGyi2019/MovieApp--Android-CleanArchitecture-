package com.example.netwrok.di

import android.content.Context
import com.chingyi.network.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {

    private var retrofit: Retrofit? = null

    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        if (retrofit == null) {
            val baseUrl = BuildConfig.BASE_URL

            val moshi = Moshi.Builder()
//                    .add(LocalDateJsonAdapter())
//                    .add(FaqCategoryJsonAdapter())
//                    .add(BallotExampleCategoryJsonAdapter())
                    .build()

            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(okHttpClient)
                    .build()
        }
        return retrofit!!
    }

    fun retrofit(context: Context): Retrofit {
        return retrofit(OkHttpProvider.okHttpClient(context))
    }
}
