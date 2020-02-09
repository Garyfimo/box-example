package com.garyfimo.breakingbadinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.garyfimo.breakingbadinfo.data.personaje.PersonajeRepository
import com.garyfimo.breakingbadinfo.ui.perfil.PersonajePerfilFragment
import kotlinx.coroutines.FlowPreview
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import kotlin.random.Random

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by lazy { (applicationContext as KodeinAware).kodein }

    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mostrarPersonajePerfil()
    }

    private fun mostrarPersonajePerfil() {
        PersonajePerfilFragment
            .newInstance(id = 1).let {
                supportFragmentManager.beginTransaction()
                    .add(R.id.flContent, it)
                    .commit()
            }
    }
}
