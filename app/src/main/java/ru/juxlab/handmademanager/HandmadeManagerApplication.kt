package ru.juxlab.handmademanager

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class HandmadeManagerApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy{
        import(androidXModule(this@HandmadeManagerApplication))
    }
}