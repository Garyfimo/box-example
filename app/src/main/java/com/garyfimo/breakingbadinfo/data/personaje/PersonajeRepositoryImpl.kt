package com.garyfimo.breakingbadinfo.data.personaje

import com.dropbox.android.external.store4.*
import com.garyfimo.breakingbadinfo.data.base.BaseRepository
import com.garyfimo.breakingbadinfo.data.database.DatabaseModel
import com.garyfimo.breakingbadinfo.data.network.Api
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import java.lang.IllegalArgumentException

class PersonajeRepositoryImpl(
    private val api: Api,
    private val databaseModel: DatabaseModel
) : PersonajeRepository, BaseRepository() {

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun fetchPersonajes(): Store<Unit, List<Personaje>> {

        return createBox(
            {
                safeApiCall(
                    call = { api.fetchPersonajes().await() },
                    errorMessage = ""
                )
            },
            { databaseModel.personajeDao().getAll() },
            { personajes -> databaseModel.personajeDao().insertAll(personajes) }
        )
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun fetchPersonajePorId(): Store<String, Personaje> {

        return createBox(
            { id -> callFetcPersonajePorId(id) },
            { id -> databaseModel.personajeDao().findById(id) },
            { personaje -> databaseModel.personajeDao().insertAll(listOf(personaje)) }
        )
    }

    private suspend fun callFetcPersonajePorId(id: String): Personaje {
        val response = safeApiCall(
            call = { api.fetchPersonajePorId(id).await() },
            errorMessage = ""
        )

        if (response.isEmpty())
            throw IllegalArgumentException()

        return response.first()
    }
}