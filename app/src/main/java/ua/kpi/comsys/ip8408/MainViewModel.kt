package ua.kpi.comsys.ip8408

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val state = MutableLiveData(ApplicationState.StudentInfo)
    var prevState = -1
}