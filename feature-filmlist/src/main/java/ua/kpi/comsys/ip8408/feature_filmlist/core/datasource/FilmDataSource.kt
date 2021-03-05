package ua.kpi.comsys.ip8408.feature_filmlist.core.datasource

import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8408.feature_filmlist.core.model.Film

interface FilmDataSource {
    suspend fun getFilmList(fileName: String): Result<List<Film>, Exception>
}