package com.garyfimo.breakingbadinfo.ui.util

import android.content.Context
import android.view.View
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.base.DefaultFailureHandler
import org.hamcrest.Matcher

class CustomFailureHandler(context : Context) : FailureHandler {

    private val delegate = DefaultFailureHandler(context)

    override fun handle(error: Throwable?, viewMatcher: Matcher<View>?) {
        try {
            delegate.handle(error, viewMatcher)
        } catch (e: NoMatchingViewException) {
            // For example done device dump, take screenshot, etc.
            throw e
        }
    }
}