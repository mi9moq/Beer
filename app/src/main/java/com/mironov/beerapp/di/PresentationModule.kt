package com.mironov.beerapp.di

import com.mironov.beerapp.presentation.info.BeerInfoViewModel
import com.mironov.beerapp.presentation.main.BeersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module =
    module {
        viewModel {
            BeersViewModel(useCase = get())
            BeerInfoViewModel(useCase = get())
        }
    }