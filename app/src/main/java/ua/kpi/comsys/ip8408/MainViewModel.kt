package ua.kpi.comsys.ip8408

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var prev: ApplicationStage? = null
    val stage = MutableLiveData<ApplicationStage>(ApplicationStage.StudentInfo)

    fun changeStage(nextStage: ApplicationStage) {
        val current = stage.value
        if (current != nextStage) {
            prev = current
            stage.value = nextStage
        }
    }
}