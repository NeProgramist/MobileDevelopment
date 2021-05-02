package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ua.kpi.comsys.ip8408.feature_imagelist.R

class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {
    private var data: List<Int> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ImageViewHolder(inflater.inflate(R.layout.recycler_image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.image)

        fun bind(item: Int) {

        }
    }
}