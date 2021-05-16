package ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository.FilmsRepository

class FilmsInterceptor(private val filmsRepository: FilmsRepository) {
    var prevQuery = ""

    suspend fun removeFilm(film: Film) = withContext(Dispatchers.IO) {
        filmsRepository.removeFilm(film)
    }

    suspend fun addFilm(film: Film) = withContext(Dispatchers.IO) {
        filmsRepository.addFilm(film)
    }
    suspend fun searchFilms(q: String) = withContext(Dispatchers.IO) {
        prevQuery = q
        filmsRepository.getFilms(q)
    }
}
