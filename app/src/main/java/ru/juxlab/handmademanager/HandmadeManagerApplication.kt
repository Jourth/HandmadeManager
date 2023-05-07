package ru.juxlab.handmademanager

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ru.juxlab.handmademanager.data.HandmadeManagerDao
import ru.juxlab.handmademanager.data.HandmadeManagerDatabase
import ru.juxlab.handmademanager.ui.edit_hmobject.HandmadeObjectViewModelFactory
import ru.juxlab.handmademanager.ui.home.HomeViewModelFactory

class HandmadeManagerApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy{
        import(androidXModule(this@HandmadeManagerApplication))

        bind<HandmadeManagerDatabase>() with singleton { HandmadeManagerDatabase(instance()) }
        bind<HandmadeManagerDao>() with singleton { instance<HandmadeManagerDatabase>().handmadeManagerDao() }
        bind() from provider { HandmadeObjectViewModelFactory(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }
}
