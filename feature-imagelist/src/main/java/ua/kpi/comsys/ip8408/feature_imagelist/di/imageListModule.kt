package ua.kpi.comsys.ip8408.feature_imagelist.di

import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.kpi.comsys.ip8408.data.utils.provideApi
import ua.kpi.comsys.ip8408.feature_imagelist.data.remote.ImagesApi
import ua.kpi.comsys.ip8408.feature_imagelist.data.remote.ImagesDataSource
import ua.kpi.comsys.ip8408.feature_imagelist.ui.ImageListViewModel

@ExperimentalSerializationApi
val imageListModule = module {
    single { provideApi(ImagesApi::class.java, "https://pixabay.com/", get()) }
    single { ImagesDataSource(get()) }

    viewModel { ImageListViewModel(get()) }
}
