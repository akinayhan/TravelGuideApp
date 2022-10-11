package com.akinayhan.travelguideapp.ui.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.akinayhan.travelguideapp.TravelApplication
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getContentViewLayoutResId(): Int

    @Inject
    @ApplicationContext
    lateinit var appContext: Context

    lateinit var travelApp: TravelApplication

    abstract fun populateUI(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        travelApp = appContext as TravelApplication

        if (this !is BaseBindingActivity<*>) {
            setContentView(getContentViewLayoutResId())
            populateUI(savedInstanceState)
        }
    }

}

