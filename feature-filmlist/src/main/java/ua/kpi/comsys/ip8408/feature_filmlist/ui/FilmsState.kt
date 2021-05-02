package ua.kpi.comsys.ip8408.feature_filmlist.ui

import ua.kpi.comsys.ip8408.core_ui.utils.Stage

sealed class FilmsState(weight: Int) : Stage(weight) {
    object Cancel : FilmsState(0)
    object FilmList : FilmsState(1)
    data class FilmDetailed(val id: String) : FilmsState(2)
}
