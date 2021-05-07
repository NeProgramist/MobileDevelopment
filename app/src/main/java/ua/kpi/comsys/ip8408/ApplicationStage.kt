package ua.kpi.comsys.ip8408

import ua.kpi.comsys.ip8408.core_ui.utils.Stage

sealed class ApplicationStage(weight: Int) : Stage(weight) {
    object StudentInfo : ApplicationStage(0)
    object Plots : ApplicationStage(1)
    object FilmList : ApplicationStage(2)
    object ImageList : ApplicationStage(3)
}
