package ua.kpi.comsys.ip8408.feature_filmlist.core.datasource

import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film

interface FilmsDataSource {
    suspend fun getFilmList(): Result<List<Film>, Exception>
    suspend fun getFilm(id: String): Result<Film, Exception>
}