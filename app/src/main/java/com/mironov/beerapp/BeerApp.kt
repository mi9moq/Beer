package com.mironov.beerapp

import android.app.Application
import com.mironov.beerapp.di.provideDataModule
import com.mironov.beerapp.di.provideDatabaseModule
import com.mironov.beerapp.di.provideDomainModule
import com.mironov.beerapp.di.provideNetworkModule
import com.mironov.beerapp.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeerApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BeerApp)
            modules(
                provideDataModule(),
                provideDomainModule(),
                provideDatabaseModule(),
                provideNetworkModule(),
                providePresentationModule(),
            )
        }
    }
}