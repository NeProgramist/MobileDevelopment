package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ua.kpi.comsys.ip8408.core_ui.utils.getImageDrawable
import ua.kpi.comsys.ip8408.feature_imagelist.R

class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {
    private var data: List<Uri> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ImageViewHolder(inflater.inflate(R.layout.recycler_image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun updateImageList(uri: List<Uri>) {
        val diffUtils = ImageListDiffUtils(data, uri)
        val imageDiffResult = DiffUtil.calculateDiff(diffUtils)

        data = uri
        imageDiffResult.dispatchUpdatesTo(this)
    }

    class ImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val image = itemView.findViewById<ImageView>(R.id.image)

        fun bind(uri: Uri) {
            val drawable = view.context.getImageDrawable(uri)
            image.setImageDrawable(drawable)
        }
    }
}