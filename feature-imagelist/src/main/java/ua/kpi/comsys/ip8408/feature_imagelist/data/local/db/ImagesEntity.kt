package ua.kpi.comsys.ip8408.feature_imagelist.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
class ImagesEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "image_url") val imageUrl: String,
)
