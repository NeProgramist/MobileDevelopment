package ua.kpi.comsys.ip8408.feature_plots.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.kpi.comsys.ip8408.feature_plots.ui.PlotsViewModel

val plotsModule = module {
    viewModel { PlotsViewModel() }
}