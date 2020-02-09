package com.garyfimo.breakingbadinfo.ui.perfil


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.garyfimo.breakingbadinfo.R
import com.garyfimo.breakingbadinfo.data.personaje.Personaje
import com.garyfimo.breakingbadinfo.ui.base.LiveDataBase
import com.garyfimo.breakingbadinfo.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_personaje_perfil.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


class PersonajePerfilFragment : ScopedFragment(), KodeinAware {

    private var personajeId = -1

    override val kodein: Kodein by lazy { (context as KodeinAware).kodein }

    private val viewModelFactory: PersonajePerfilViewModelFactory by instance()

    private val viewModel: PersonajePerfilViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personajeId = it.getInt(
                PERSONAJE_ID
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personaje_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.personajeState.observe(::getLifecycle, ::updatePersonajeStateUI)
        viewModel.personaje.observe(::getLifecycle, ::updatePersonajeUI)
        configurarClickListener()
        getPersonaje()
    }

    private fun configurarClickListener() {
        btnAdivinar?.setOnClickListener {
            buscarOtroPersonajeRandom()
        }
    }

    private fun buscarOtroPersonajeRandom() {
        launch { viewModel.getPersonajeRandom() }
    }

    private fun getPersonaje() {
        launch { viewModel.getPersonajePorId(personajeId.toString()) }
    }

    private fun updatePersonajeStateUI(personajeState: LiveDataBase) {
        when (personajeState) {
            is LiveDataBase.ScreenState -> updateScreenState(personajeState)
            is LiveDataBase.ErrorState -> updateErrorState(personajeState)
        }
    }

    private fun updateErrorState(personajeState: LiveDataBase.ErrorState) {
        when (personajeState) {
            is LiveDataBase.ErrorState.ErrorControlado -> Log.d(
                "LOOOOOOG",
                personajeState.mensaje
            )
        }
    }

    private fun updateScreenState(personajeState: LiveDataBase.ScreenState) {
        when (personajeState) {
            LiveDataBase.ScreenState.Cargando -> Log.d("LOOOOOOG", "Cargando")
            LiveDataBase.ScreenState.NoCargando -> Log.d("LOOOOOOG", "No cargando")
        }
    }

    private fun updatePersonajeUI(personaje: Personaje) {
        tvNombre?.text = personaje.nombre
        tvApodo?.text = personaje.apodo
        tvCumpleanios?.text = personaje.cumpleanios
        tvEstado?.text = personaje.estado
        Glide.with(context!!)
            .load(personaje.imagenPerfil)
            .into(ivFotoPerfil)
    }

    companion object {

        const val PERSONAJE_ID = "personaje_id"

        @JvmStatic
        fun newInstance(id: Int) =
            PersonajePerfilFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        PERSONAJE_ID, id
                    )
                }
            }
    }
}
