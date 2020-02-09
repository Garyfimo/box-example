package com.garyfimo.breakingbadinfo.ui.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garyfimo.breakingbadinfo.data.personaje.PersonajeRepository

class PersonajePerfilViewModelFactory(
    private val personajeRepository: PersonajeRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonajePerfilViewModel(personajeRepository) as T
    }
}