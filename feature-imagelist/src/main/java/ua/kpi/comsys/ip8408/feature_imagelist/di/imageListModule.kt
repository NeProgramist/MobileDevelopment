package ua.kpi.comsys.ip8408.feature_imagelist.di

import androidx.room.Room
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.kpi.comsys.ip8408.data.di.local
import ua.kpi.comsys.ip8408.data.di.remote
import ua.kpi.comsys.ip8408.data.frameworks.remote.provideApi
import ua.kpi.comsys.ip8408.feature_imagelist.core.datasource.ImagesDataSource
import ua.kpi.comsys.ip8408.feature_imagelist.core.domain.repository.ImagesRepository
import ua.kpi.comsys.ip8408.feature_imagelist.data.local.ImagesLocalDataSource
import ua.kpi.comsys.ip8408.feature_imagelist.data.local.db.ImagesDatabase
import ua.kpi.comsys.ip8408.feature_imagelist.data.remote.ImagesApi
import ua.kpi.comsys.ip8408.feature_imagelist.data.remote.ImagesRemoteDataSource
import ua.kpi.comsys.ip8408.feature_imagelist.ui.ImageListViewModel

@ExperimentalSerializationApi
val imageListModule = module {
    single { provideApi(ImagesApi::class.java, "https://pixabay.com/", get()) }
    single {
        Room.databaseBuilder(get(), ImagesDatabase::class.java, "images-db").build().imagesDao()
    }

    single<ImagesDataSource>(local) { ImagesLocalDataSource(get()) }
    single<ImagesDataSource>(remote) { ImagesRemoteDataSource(get()) }

    single { ImagesRepository(get(local), get(remote)) }

    viewModel { ImageListViewModel(get()) }
}
