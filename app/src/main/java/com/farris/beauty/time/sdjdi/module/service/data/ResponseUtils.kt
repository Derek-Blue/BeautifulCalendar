package com.farris.beauty.time.sdjdi.module.service.data

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import retrofit2.Response

@Throws(
    SerializationException::class,
    IllegalArgumentException::class
)
fun <T : Response<R>, R> T.checkIsSuccessful(): T {
    if (this.isSuccessful) {
        return this
    } else {
        throw HttpException(this)
    }
}

@Throws(NullPointerException::class)
fun <T : Response<R>, R> T.requireBody(): R {
    return this.body() ?: throw NullPointerException("service response body is null")
}