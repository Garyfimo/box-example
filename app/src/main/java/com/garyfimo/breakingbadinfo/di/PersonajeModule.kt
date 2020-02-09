package com.garyfimo.breakingbadinfo.di

import com.garyfimo.breakingbadinfo.data.personaje.PersonajeRepository
import com.garyfimo.breakingbadinfo.data.personaje.PersonajeRepositoryImpl
import com.garyfimo.breakingbadinfo.ui.perfil.PersonajePerfilViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val personajeModule = Kodein.Module("personaje") {
    bind<PersonajeRepository>() with singleton {
        PersonajeRepositoryImpl(instance(), instance())
    }
    bind() from provider { PersonajePerfilViewModelFactory(instance()) }
}