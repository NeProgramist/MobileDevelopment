package ua.kpi.comsys.ip8408.feature_filmlist.data.local

import android.util.Log
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.db.FilmBasic
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.db.FilmsDao
import ua.kpi.comsys.ip8408.feature_filmlist.data.mappers.toFilmEntity
import ua.kpi.comsys.ip8408.feature_filmlist.data.mappers.toFilm

class FilmsLocalDataSource(private val filmsDao: FilmsDao): FilmsDataSource {
    private val tag = this::class.simpleName

    override suspend fun getFilmList(request: String) = try {
        val filmList = filmsDao.getFilmList(request)
        if (filmList.isNotEmpty()) {
            val mapped = filmList.map(FilmBasic::toFilm)
            Ok(mapped)
        } else {
            Err(Exception("No film cached with request \"$request\""))
        }
    } catch (e: Exception) {
        Log.e(tag, "getFilmList: ${e.message}")
        Err(Exception("Can't get film list locally"))
    }

    override suspend fun getFilmDetailed(id: String) = try {
        val filmDetailed = filmsDao.getFilmDetailed(id).toFilm()
        Ok(filmDetailed)
    } catch (e: Exception) {
        Log.e(tag, "getFilmDetailed: ${e.message}")
        Err(Exception("Can't get detailed info locally"))
    }

    override suspend fun saveFilmDetailed(film: Film) {
        try {
            val filmDetailedEntity = film.toFilmEntity()
            filmsDao.update(filmDetailedEntity)
        } catch (e: Exception) {
            Log.e(tag, "saveFilmDetailed: ${e.message}")
        }
    }

    override suspend fun saveFilmList(filmList: List<Film>) {
        try {
            val filmListEntity = filmList.map { it.toFilmEntity() }
            filmsDao.insertAll(filmListEntity)
        } catch (e: Exception) {
            Log.e(tag, "saveFilmDetailed: ${e.message}")
        }
    }

    override suspend fun deleteFilm(film: Film) {
        try {
            val filmEntity = film.toFilmEntity()
            filmsDao.delete(filmEntity)
        } catch (e: Exception) {
            Log.e(tag, "deleteFilm: ${e.message}")
        }
    }
}
