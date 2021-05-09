package ua.kpi.comsys.ip8408.feature_filmlist.ui.film_list

import androidx.recyclerview.widget.DiffUtil
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film

class FilmListDiffUtils(private val old: List<Film>, private val new: List<Film>) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size
    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] === new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] === new[newItemPosition]
    }
}