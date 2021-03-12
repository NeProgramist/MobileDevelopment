package ua.kpi.comsys.ip8408.data.frameworks.di

import org.koin.dsl.module
import ua.kpi.comsys.ip8408.data.frameworks.local.AssetsReader

val dataModule = module {
    single { AssetsReader(get()) }
}