package ua.kpi.comsys.ip8408.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ua.kpi.comsys.ip8408.data.frameworks.di.dataModule
import ua.kpi.comsys.ip8408.feature_filmlist.di.filmListModule

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(filmListModule)
            modules(dataModule)
        }
    }
}