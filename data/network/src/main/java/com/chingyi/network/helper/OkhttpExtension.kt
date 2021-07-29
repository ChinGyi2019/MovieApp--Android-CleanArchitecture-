package com.example.netwrok.helper


import com.chingyi.domain.exception.NetworkException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Response
import okhttp3.ResponseBody

suspend fun Response.executeOrThrow(call:suspend  ()-> Response): ResponseBody {

    val response = withContext(Dispatchers.IO){ call.invoke() }

    return response.getBodyOrThrowNetworkException()
}

fun Response.getBodyOrThrowNetworkException(): ResponseBody {

    if (this.isSuccessful.not()) {
        val errorString = this.body!!.byteStream()
                .bufferedReader()
                .use { it.readText() }
        throw NetworkException(errorString, this.code)
    }

    val body = this.body ?: throw NetworkException()

    return body
}
