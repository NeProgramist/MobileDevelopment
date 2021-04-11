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
    val films = MutableLiveData<List<Film>>()
    val filmsException = MutableLiveData<Exception>()

    private var job: Job? = null

    fun getFilms() {
        viewModelScope.launch {
            val res = filmsInterceptor.getFilms()

            res.fold(
                { films.postValue(it) },
                { filmsException.postValue(it) }
            )
        }
    }

    fun removeFilm(film: Film): Boolean {
       return filmsInterceptor.removeFilm(film).mapBoth({ true }, { false })
    }

    fun addFilm(film: Film) {
        viewModelScope.launch {
            val res = filmsInterceptor.addFilm(film)

            res.fold(
                { films.postValue(it) },
                { filmsException.postValue(it) }
            )
        }
    }

    fun onTextChanged(text: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(500)

            val res = filmsInterceptor.searchFilms(text)

            res.fold(
                { films.postValue(it) },
                { filmsException.postValue(it) }
            )
        }
    }
}
