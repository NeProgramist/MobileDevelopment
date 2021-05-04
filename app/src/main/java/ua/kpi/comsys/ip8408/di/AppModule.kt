package ua.kpi.comsys.ip8408.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.kpi.comsys.ip8408.MainViewModel

val appModule = module {
    viewModel { MainViewModel() }
}