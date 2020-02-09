package com.garyfimo.breakingbadinfo.core

import android.app.Application
import androidx.room.Room
import com.garyfimo.breakingbadinfo.data.database.DatabaseModel
import com.garyfimo.breakingbadinfo.di.apiModule
import com.garyfimo.breakingbadinfo.di.databaseModule
import com.garyfimo.breakingbadinfo.di.personajeModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class BreakingBadInfoApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@BreakingBadInfoApp))

        importOnce(apiModule)
        importOnce(databaseModule)
        importOnce(personajeModule)
    }

    override fun onCreate() {
        super.onCreate()
    }
}