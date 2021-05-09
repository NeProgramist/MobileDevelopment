package ua.kpi.comsys.ip8408.feature_filmlist.data.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.data.mappers.toFilm

class FilmsRemoteDataSource(private val api: FilmsApi): FilmsDataSource {
    override suspend fun getFilmList(request: String): Result<List<Film>, Exception> {
        val res = api.getFilms(request)
        val films = res.result

        return when {
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
    }

    override suspend fun getFilm(id: String): Result<Film, Exception> {
        val res = api.getFilm(id)

        return if (res.success == "False") Err(Exception(res.error)) else Ok(res.toFilm())
    }
}
