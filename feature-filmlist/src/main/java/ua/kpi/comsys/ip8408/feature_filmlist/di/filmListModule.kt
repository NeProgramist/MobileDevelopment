package ua.kpi.comsys.ip8408.feature_filmlist.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.FilmsRemoteDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsAdapter
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsViewModel

val filmListModule = module {

    single { FilmsAdapter(assetsDS = get()) }
    single<FilmsDataSource> { FilmsRemoteDataSource(get()) }
    viewModel { FilmsViewModel(get()) }
}