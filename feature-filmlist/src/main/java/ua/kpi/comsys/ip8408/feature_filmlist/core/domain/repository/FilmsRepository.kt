package ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository

import com.github.michaelbull.result.*
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film

class FilmsRepository(
    private val local: FilmsDataSource,
    private val remote: FilmsDataSource,
) {
    suspend fun getFilms(request: String) = remote.getFilmList(request)
        .onSuccess { local.saveFilmList(it) }
        .orElse { local.getFilmList(request) }

    suspend fun getFilmDetailed(id: String) = remote.getFilmDetailed(id)
        .onSuccess { local.saveFilmDetailed(it) }
        .orElse { local.getFilmDetailed(id) }

    suspend fun addFilm(film: Film) = local.saveFilmList(listOf(film))
    suspend fun removeFilm(film: Film) = local.deleteFilm(film)
}
