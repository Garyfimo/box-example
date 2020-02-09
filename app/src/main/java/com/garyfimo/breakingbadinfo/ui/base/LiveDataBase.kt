package com.garyfimo.breakingbadinfo.ui.base

sealed class LiveDataBase {

    sealed class ErrorState : LiveDataBase() {
        class ErrorControlado(val mensaje: String) : ErrorState()
        object ErrorNoControlado : ErrorState()
    }

    sealed class ScreenState : LiveDataBase() {
        object Cargando : ScreenState()
        object NoCargando : ScreenState()
        object Exito : ScreenState()
    }
}