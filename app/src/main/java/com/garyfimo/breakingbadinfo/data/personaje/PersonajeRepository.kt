package com.garyfimo.breakingbadinfo.data.personaje

import com.dropbox.android.external.store4.Store

interface PersonajeRepository {

    suspend fun fetchPersonajes(): Store<Unit, List<Personaje>>
    suspend fun fetchPersonajePorId(): Store<String, Personaje>
}