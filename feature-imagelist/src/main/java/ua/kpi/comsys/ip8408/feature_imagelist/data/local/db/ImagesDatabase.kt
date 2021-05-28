package ua.kpi.comsys.ip8408.feature_imagelist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [ImagesEntity::class], exportSchema = false)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao
}
