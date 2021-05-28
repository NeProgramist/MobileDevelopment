package ua.kpi.comsys.ip8408.feature_filmlist.di

import androidx.room.Room
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.kpi.comsys.ip8408.data.di.local
import ua.kpi.comsys.ip8408.data.di.remote
import ua.kpi.comsys.ip8408.data.frameworks.remote.provideApi
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor.FilmInterceptor
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor.FilmsInterceptor
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository.FilmsRepository
import ua.kpi.comsys.ip8408.feature_filmlist.data.remote.FilmsApi
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.FilmsLocalDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.db.FilmsDatabase
import ua.kpi.comsys.ip8408.feature_filmlist.data.remote.FilmsRemoteDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsViewModel
import ua.kpi.comsys.ip8408.feature_filmlist.ui.detailed.FilmDetailedViewModel
import ua.kpi.comsys.ip8408.feature_filmlist.ui.film_list.FilmListViewModel

@ExperimentalSerializationApi
val filmListModule = module {
    single { provideApi(FilmsApi::class.java, "http://www.omdbapi.com", get()) }
    single { Room.databaseBuilder(get(), FilmsDatabase::class.java, "films-db").build().filmsDao() }

    single<FilmsDataSource>(local) { FilmsLocalDataSource(get()) }
    single<FilmsDataSource>(remote) { FilmsRemoteDataSource(get()) }

    single { FilmsRepository(get(local), get(remote)) }

    single { FilmInterceptor(get()) }
    single { FilmsInterceptor(get()) }

    viewModel { FilmsViewModel() }
    viewModel { FilmListViewModel(get()) }
    viewModel { FilmDetailedViewModel (get()) }
}
