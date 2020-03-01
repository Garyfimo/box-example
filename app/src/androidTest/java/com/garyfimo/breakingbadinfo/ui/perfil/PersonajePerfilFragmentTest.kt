package com.garyfimo.breakingbadinfo.ui.perfil

import BaseTest
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.garyfimo.breakingbadinfo.R
import com.garyfimo.breakingbadinfo.ui.util.EspressoIdlingResource
import com.garyfimo.breakingbadinfo.ui.util.checkDisplayed
import com.garyfimo.breakingbadinfo.ui.util.viewWithId
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PersonajePerfilFragmentTest : BaseTest() {

    @Before
    fun registrarIdleResources(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun borrarIdleResources(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun debeMostrarCargandoAlIniciar() {
        viewWithId(R.id.ivFotoPerfilCargando).checkDisplayed()
        viewWithId(R.id.viewNombreCargando).checkDisplayed()
        viewWithId(R.id.viewTituloApodoCargando).checkDisplayed()
        viewWithId(R.id.viewApodoCargando).checkDisplayed()
        viewWithId(R.id.viewTituloCumpleaniosCargando).checkDisplayed()
        viewWithId(R.id.viewCumpleaniosCargando).checkDisplayed()
        viewWithId(R.id.viewTituloEstadoCargando).checkDisplayed()
        viewWithId(R.id.viewEstadoCargando).checkDisplayed()
    }

    @Test
    fun debeMostrarBotonAdivinar() {
        viewWithId(R.id.btnAdivinar).checkDisplayed()
    }

    @Test
    fun debeMostrarDatosAlDarClickEnAdivinar() {
        viewWithId(R.id.btnAdivinar).perform(click())
        viewWithId(R.id.tvCumpleanios).checkDisplayed()
    }
}