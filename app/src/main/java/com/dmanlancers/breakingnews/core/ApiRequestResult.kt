package com.dmanlancers.breakingnews.core

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

abstract class ApiRequestResult {
    suspend fun <T : Any> apiRequestResult(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val responseErr = response.errorBody()?.string()
            val message = StringBuilder()
            responseErr.let {
                try {
                    message.append(JSONObject(it).getString("error"))
                } catch (e: JSONException) {
                }
            }
            Timber.tag("TAG").d("apiRequestResult: %s", message.toString())
            throw Exception(message.toString())
        }
    }
}