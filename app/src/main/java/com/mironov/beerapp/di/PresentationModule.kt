package com.mironov.beerapp.di

import com.mironov.beerapp.presentation.info.BeerInfoViewModel
import com.mironov.beerapp.presentation.main.BeersViewModel
import com.mironov.beerapp.presentation.random.RandomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

fun providePresentationModule(): Module =
    module {
        viewModel {
            BeersViewModel(useCase = get())
            BeerInfoViewModel(useCase = get())
            RandomViewModel(getRandomBeerUseCase = get())
        }
        viewModelOf(::BeersViewModel)
        viewModelOf(::BeerInfoViewModel)
    }