package ua.kpi.comsys.ip8408.feature_filmlist.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.michaelbull.result.fold
import ua.kpi.comsys.ip8408.data.frameworks.local.AssetsReader
import ua.kpi.comsys.ip8408.feature_filmlist.R
import ua.kpi.comsys.ip8408.feature_filmlist.core.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.databinding.ItemFilmBinding

class FilmsAdapter(
    private var data: List<Film> = listOf(),
    private val assetsDS: AssetsReader,
) : RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun updateDataSet(new: List<Film>) {
        data = new
        notifyDataSetChanged()
    }

    inner class FilmsViewHolder(
        private val binding: ItemFilmBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Film) = with(binding) {
            assetsDS.open(item.poster).fold(
                { poster.setImageBitmap(it) },
                { poster.setImageResource(R.drawable.pic_no_poster) }
            )

            year.text = if (item.year.isBlank()) "[no year]" else item.year
            title.text = if (item.title.isBlank()) "[no title]" else item.title

            val tp = if (item.type.isBlank()) "[no type]" else item.type
            type.text = root.resources.getString(R.string.item_film_type, tp)

            val imdbId = if (item.imdbId.isBlank()) "[no imdbId]" else item.imdbId
            id.text = root.resources.getString(R.string.item_film_id, imdbId)
        }
    }
}