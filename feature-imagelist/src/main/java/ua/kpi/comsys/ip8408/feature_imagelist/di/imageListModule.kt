package ua.kpi.comsys.ip8408.feature_imagelist.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.kpi.comsys.ip8408.feature_imagelist.ui.ImageListViewModel

val imageListModule = module {
    viewModel { ImageListViewModel() }
}
