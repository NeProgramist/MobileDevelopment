package ua.kpi.comsys.ip8408.feature_filmlist.core.datasource

import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film

interface FilmsDataSource {
    suspend fun getFilmList(request: String): Result<List<Film>, Exception>
    suspend fun getFilmDetailed(id: String): Result<Film, Exception>
    suspend fun saveFilmDetailed()
    suspend fun saveFilmList()
}