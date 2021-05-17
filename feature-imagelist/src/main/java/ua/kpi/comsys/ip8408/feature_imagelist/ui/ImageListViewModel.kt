package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.fold
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8408.feature_imagelist.core.domain.repository.ImagesRepository
import ua.kpi.comsys.ip8408.feature_imagelist.data.mappers.toUri
import ua.kpi.comsys.ip8408.feature_imagelist.core.domain.model.ImageInfo

class ImageListViewModel(private val imagesRepository: ImagesRepository) : ViewModel() {
    val images = MutableLiveData<List<Uri>>()
    val imagesException = MutableLiveData<Exception>()
    private val randomIdRange = (0..999999)

    fun addImage(uri: Uri) {
        viewModelScope.launch {
            imagesRepository.addImage(ImageInfo(randomIdRange.random(), uri.toString()))
        }
    }

    fun getImages() {
        viewModelScope.launch {
            imagesRepository.getImages().fold(
                { images.postValue(it.map(ImageInfo::toUri)) },
                { imagesException.postValue(it) },
            )
        }
    }
}