package ua.kpi.comsys.ip8408.feature_filmlist.data.remote

import android.util.Log
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.data.mappers.toFilm

class FilmsRemoteDataSource(private val api: FilmsApi): FilmsDataSource {
    private val tag = this::class.simpleName

    override suspend fun getFilmList(request: String) = try {
        val res = api.getFilms(request)
        val films = res.result

        when {
            res.success == "False" -> {
                Err(Exception(res.error))
            }
            films.isNullOrEmpty() -> {
                Err(Exception("No films"))
            }
            else -> {
                Ok(films)
            }
        }
    } catch (e: Exception) {
        Log.e(tag, "getFilmList: ${e.message}")
        Err(Exception("Error occurred in get film list method: ${e.message}"))
    }

    override suspend fun getFilmDetailed(id: String) = try {
        val res = api.getFilm(id)
        if (res.success == "False") Err(Exception(res.error)) else Ok(res.toFilm())
    } catch (e: Exception) {
        Log.e(tag, "getFilmDetailed: ${e.message}")
        Err(Exception("Error occurred in get film detailed method: ${e.message}"))
    }

    override suspend fun saveFilmDetailed(film: Film) = Unit
    override suspend fun saveFilmList(filmList: List<Film>) = Unit
    override suspend fun deleteFilm(film: Film) = Unit
}
