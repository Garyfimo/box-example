package com.garyfimo.breakingbadinfo.data.network

import com.garyfimo.breakingbadinfo.BuildConfig.BREAKING_BAD_API_URL
import com.garyfimo.breakingbadinfo.data.personaje.Personaje
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface Api {

    @GET("api/characters/{id}")
    fun fetchPersonajePorId(@Path("id") id: String): Deferred<Response<List<Personaje>>>

    @GET("api/characters")
    fun fetchPersonajes(): Deferred<Response<List<Personaje>>>


    companion object {

        operator fun invoke(): Api {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = (HttpLoggingInterceptor.Level.BODY)


            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BREAKING_BAD_API_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }
}