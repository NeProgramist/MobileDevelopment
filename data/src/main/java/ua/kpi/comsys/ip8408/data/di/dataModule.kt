package ua.kpi.comsys.ip8408.data.di

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import ua.kpi.comsys.ip8408.data.frameworks.local.AssetsReader

val local = qualifier("local")
val remote = qualifier("remote")

val dataModule = module {
    single { AssetsReader(get()) }
}