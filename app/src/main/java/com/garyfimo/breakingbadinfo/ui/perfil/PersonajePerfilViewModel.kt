package com.garyfimo.breakingbadinfo.ui.perfil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dropbox.android.external.store4.get
import com.garyfimo.breakingbadinfo.data.personaje.Personaje
import com.garyfimo.breakingbadinfo.data.personaje.PersonajeRepository
import com.garyfimo.breakingbadinfo.ui.base.LiveDataBase
import com.garyfimo.breakingbadinfo.ui.util.EspressoIdlingResource
import kotlin.random.Random

class PersonajePerfilViewModel(
    private val personajeRepository: PersonajeRepository
) : ViewModel() {

    private val _personajeState = MutableLiveData<LiveDataBase>()

    val personajeState: LiveData<LiveDataBase>
        get() = _personajeState

    private val _personaje = MutableLiveData<Personaje>()

    val personaje: LiveData<Personaje>
        get() = _personaje

    suspend fun getPersonajePorId(id: String) {
        _personajeState.value = LiveDataBase.ScreenState.Cargando
        EspressoIdlingResource.increment()
        try {
            val personaje = personajeRepository
                .fetchPersonajePorId()
                .get(id)
            if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                EspressoIdlingResource.decrement()
            }
            _personajeState.value = LiveDataBase.ScreenState.NoCargando
            _personaje.value = personaje
            _personajeState.value = LiveDataBase.ScreenState.Exito
        } catch (ex: Exception) {
            _personajeState.value = LiveDataBase.ErrorState.ErrorControlado(ex.message!!)
        }
    }

    suspend fun getPersonajeRandom() {
        getPersonajePorId(getRandomNumber())
    }

    private fun getRandomNumber(): String {
        return Random.nextInt(1, 30).toString()
    }
}