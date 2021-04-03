package ua.kpi.comsys.ip8408.feature_filmlist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.fold
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor.FilmsInterceptor
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import kotlin.Exception

class FilmsViewModel : ViewModel() {
    val state = MutableLiveData<FilmsState>(FilmsState.FilmList)

    fun back() {
        val next: FilmsState = when(state.value) {
            is FilmsState.FilmDetailed -> FilmsState.FilmList
            else -> FilmsState.Cancel
        }

        state.value = next
    }
}
