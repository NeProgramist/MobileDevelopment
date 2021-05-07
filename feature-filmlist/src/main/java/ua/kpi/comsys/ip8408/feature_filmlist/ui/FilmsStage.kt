package ua.kpi.comsys.ip8408.feature_filmlist.ui

import ua.kpi.comsys.ip8408.core_ui.utils.Stage

sealed class FilmsStage (weight: Int) : Stage(weight) {
    object Cancel : FilmsStage(0)
    object FilmList : FilmsStage(1)
    data class FilmDetailed(val id: String) : FilmsStage(2)
}
