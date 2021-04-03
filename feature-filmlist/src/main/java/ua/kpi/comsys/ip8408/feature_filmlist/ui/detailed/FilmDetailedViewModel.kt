package ua.kpi.comsys.ip8408.feature_filmlist.ui.detailed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.fold
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor.FilmInterceptor
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film

class FilmDetailedViewModel(private val filmInterceptor: FilmInterceptor) : ViewModel() {
    val film = MutableLiveData<Film>()
    val filmException = MutableLiveData<Exception>()

    fun getFilm(id: String) {
        viewModelScope.launch {
            val res = filmInterceptor.getFilm(id)

            res.fold(
                { film.postValue(it) },
                { filmException.postValue(it) }
            )
        }
    }
}
