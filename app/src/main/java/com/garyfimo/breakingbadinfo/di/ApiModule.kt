package com.garyfimo.breakingbadinfo.di

import com.garyfimo.breakingbadinfo.data.network.Api
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

val apiModule = Kodein.Module("api") {
    bind() from singleton { Api.Companion() }
}