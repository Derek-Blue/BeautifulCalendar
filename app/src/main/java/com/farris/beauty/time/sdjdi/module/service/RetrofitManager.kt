package com.farris.beauty.time.sdjdi.module.service

import com.farris.beauty.time.sdjdi.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private val MyJson = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        isLenient = true
        allowStructuredMapKeys = true
        prettyPrint = true
        explicitNulls = true
        prettyPrintIndent = "    "
        coerceInputValues = true
        useArrayPolymorphism = true
        classDiscriminator = "type"
        allowSpecialFloatingPointValues = false
        useAlternativeNames = true
    }

    private var retrofit: Retrofit? = null

    private const val BaseUrl = "https://opendata.cwb.gov.tw/"

    fun init(
        networkInterceptor: List<Interceptor> = emptyList(),
        netInterceptor: List<Interceptor> = emptyList()
    ) {
        synchronized(this) {
            val okHttpClient = createOkHttpClient(networkInterceptor, netInterceptor)
            val contentType = "application/json".toMediaType()
            retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(MyJson.asConverterFactory(contentType))
                .client(okHttpClient)
                .build()
        }
    }

    val getForecastApi: ForecastApiService by lazy {
        getRetrofit().create(ForecastApiService::class.java)
    }

    fun getBaseJson(): Json {
        return MyJson
    }

    private fun getRetrofit(): Retrofit {
        return synchronized(this) {
            retrofit ?: throw IllegalStateException("retrofit instance is null")
        }
    }

    private fun createOkHttpClient(
        networkInterceptor: List<Interceptor>,
        netInterceptor: List<Interceptor>
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT))
            .connectTimeout(30L, TimeUnit.SECONDS)
            .callTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)

        networkInterceptor.forEach {
            builder.addNetworkInterceptor(it)
        }

        netInterceptor.forEach {
            builder.addInterceptor(it)
        }

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return builder.build()
    }

}