package ua.kpi.comsys.ip8408.feature_filmlist.data.local

import android.util.Log
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.db.FilmEntity
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.db.FilmsDao
import ua.kpi.comsys.ip8408.feature_filmlist.data.mappers.toFilm

class FilmsLocalDataSource(private val filmsDao: FilmsDao): FilmsDataSource {
    private val tag = this::class.simpleName

    override suspend fun getFilmList(request: String) = try {
        val filmList = filmsDao.getFilmList(request)
        if (filmList.isNotEmpty()) {
            val mapped = filmList.map(FilmEntity::toFilm)
            Ok(mapped)
        } else {
            Err(Exception("No film cached with request \"$request\""))
        }
    } catch (e: Exception) {
        Log.e(tag, "getFilmList: ${e.message}")
        Err(Exception("Can't get film list locally"))
    }

    override suspend fun getFilmDetailed(id: String) = try {
        val film = filmsDao.getFilm(id)
        val filmDetailed = filmsDao.getFilmDetailed(id)
        Ok((film to filmDetailed).toFilm())
    } catch (e: Exception) {
        Log.e(tag, "getFilmDetailed: ${e.message}")
        Err(Exception("Can't get detailed info locally"))
    }

    override suspend fun saveFilmDetailed() {
        TODO("Not yet implemented")
    }

    override suspend fun saveFilmList() {
        TODO("Not yet implemented")
    }
}