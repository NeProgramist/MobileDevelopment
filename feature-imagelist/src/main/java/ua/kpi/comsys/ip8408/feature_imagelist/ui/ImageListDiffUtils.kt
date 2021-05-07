package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil

class ImageListDiffUtils(private val old: List<Uri>, private val new: List<Uri>) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size
    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] === new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] === new[newItemPosition]
    }
}