package ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository.FilmsRepository

class FilmsInterceptor(private val filmsRepository: FilmsRepository) {
    var prevQuery = ""

    fun removeFilm(film: Film) = filmsRepository.removeFilm(film)

    fun addFilm(film: Film) = filmsRepository.addFilm(film)

    suspend fun searchFilms(q: String) = withContext(Dispatchers.IO) {
        prevQuery = q
        filmsRepository.getFilms(q)
    }
}
