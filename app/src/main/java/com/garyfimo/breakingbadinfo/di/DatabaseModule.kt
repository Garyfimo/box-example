package com.garyfimo.breakingbadinfo.di

import com.garyfimo.breakingbadinfo.data.database.DatabaseModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val databaseModule = Kodein.Module("database") {
    bind() from singleton { DatabaseModel.Companion(instance()) }
}