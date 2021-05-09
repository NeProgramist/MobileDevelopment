package ua.kpi.comsys.ip8408.feature_filmlist.ui.film_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.fold
import com.github.michaelbull.result.mapBoth
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor.FilmsInterceptor
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film

class FilmListViewModel(private val filmsInterceptor: FilmsInterceptor) : ViewModel() {
    val prevQuery: String
        get() = filmsInterceptor.prevQuery

    val films = MutableLiveData<List<Film>>()
    val filmsException = MutableLiveData<Exception>()
    val filmActionException = MutableLiveData<Exception>()

    private var job: Job? = null

    fun restoreFilms() {
        filmsInterceptor.restoreFilms().fold(
            { films.postValue(it) },
            { filmsException.postValue(it) }
        )
    }

    fun removeFilm(film: Film): Boolean {
       return filmsInterceptor
           .removeFilm(film)
           .mapBoth(
               {
                   true
               },
               {
                   filmActionException.value = it
                   false
               },
           )
    }

    fun addFilm(film: Film) {
        viewModelScope.launch {
            filmsInterceptor.addFilm(film).fold(
                { films.postValue(it) },
                { filmActionException.postValue(it) }
            )
        }
    }

    fun onTextChanged(text: String) {
        job?.cancel()
        if (text.length > 2) {
            job = viewModelScope.launch {
                delay(300)
                filmsInterceptor.searchFilms(text).fold(
                    { films.postValue(it) },
                    { filmsException.postValue(it) }
                )
            }
        } else {
            films.value = listOf()
            filmsException.value = Exception("No films")
        }
    }
}
