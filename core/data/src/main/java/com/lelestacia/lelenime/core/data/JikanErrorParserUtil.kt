package com.lelestacia.lelenime.core.data

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.lelestacia.lelenime.core.network.model.ErrorResponse
import retrofit2.HttpException

class JikanErrorParserUtil {

    /**
     * Returns a human-readable error message for the given [Throwable].
     *
     * If the given [Throwable] is an [HttpException], attempts to parse the error response body
     * and returns a formatted message that includes the error status code and message.
     * If parsing fails, returns a generic message that the response failed to parse.
     *
     * If the given [Throwable] is not an [HttpException], returns a message that includes the
     * error message of the [Throwable].
     *
     * @param t the [Throwable] for which to generate the error message.
     * @return a human-readable error message for the given [Throwable].
     */
    operator fun invoke(t: Throwable): String {
        if (t is HttpException) {
            val errorResponse = t.response()?.errorBody()
            val gson = Gson()
            val errorAdapter: TypeAdapter<ErrorResponse> = gson.getAdapter(ErrorResponse::class.java)
            return try {
                val error = errorAdapter.fromJson(errorResponse?.string())
                "Error ${error.status}: ${error.message}"
            } catch (e: Exception) {
                "Response failed to parse"
            }
        }

        return "Error: ${t.message}"
    }
}
