package ua.kpi.comsys.ip8408.core_ui.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class StageViewModel<T : Stage> : ViewModel() {
    var prev: T? = null
    val stage = MutableLiveData<T>()

    fun changeStage(nextStage: T) {
        val current = stage.value
        if (current != nextStage) {
            prev = current
            stage.value = nextStage
        }
    }
}