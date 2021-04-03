package ua.kpi.comsys.ip8408.feature_filmlist.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor.FilmInterceptor
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor.FilmsInterceptor
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository.FilmsRepository
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.FilmsLocalDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsViewModel
import ua.kpi.comsys.ip8408.feature_filmlist.ui.detailed.FilmDetailedFragment
import ua.kpi.comsys.ip8408.feature_filmlist.ui.detailed.FilmDetailedViewModel
import ua.kpi.comsys.ip8408.feature_filmlist.ui.film_list.FilmListViewModel

val filmListModule = module {

    single<FilmsDataSource> { FilmsLocalDataSource(get()) }

    single { FilmsRepository(get()) }

    single { FilmInterceptor(get()) }
    single { FilmsInterceptor(get()) }

    viewModel { FilmsViewModel() }
    viewModel { FilmListViewModel(get()) }
    viewModel { FilmDetailedViewModel (get()) }
}
