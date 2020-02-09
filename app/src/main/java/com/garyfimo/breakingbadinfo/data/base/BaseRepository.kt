package com.garyfimo.breakingbadinfo.data.base

import com.dropbox.android.external.store4.MemoryPolicy
import com.dropbox.android.external.store4.Store
import com.dropbox.android.external.store4.StoreBuilder
import com.garyfimo.breakingbadinfo.data.database.DatabaseModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.util.concurrent.TimeUnit

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T {

        val result: Result<T> = safeApiResult(call)
        var data: T? = null

        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                throw Exception(errorMessage)
            }
        }
        return data
    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error
    }

    @UseExperimental(ExperimentalCoroutinesApi::class)
    @FlowPreview
    fun <Key : Any, Output : Any> createBox(
        response: suspend (Key) -> Output,
        reader: (Key) -> Flow<Output>,
        writer: suspend (Output) -> Unit
    ): Store<Key, Output> {
        return StoreBuilder
            .fromNonFlow<Key, Output> { id -> response.invoke(id) }
            .persister(
                reader = { id -> reader.invoke(id) },
                writer = { _, entities -> writer.invoke(entities) },
                delete = {}
            )
            .cachePolicy(
                MemoryPolicy
                    .builder()
                    .setExpireAfterWrite(100)
                    .setExpireAfterTimeUnit(TimeUnit.SECONDS)
                    .build()
            )
            .build()
    }
}