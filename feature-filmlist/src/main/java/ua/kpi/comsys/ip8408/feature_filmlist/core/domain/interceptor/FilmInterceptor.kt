package ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor

import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository.FilmsRepository

class FilmInterceptor(private val filmsRepository: FilmsRepository) {
    suspend fun getFilm(id: String) = filmsRepository.getFilmDetailed(id)
}