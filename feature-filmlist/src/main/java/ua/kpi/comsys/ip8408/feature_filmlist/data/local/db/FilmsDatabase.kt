package ua.kpi.comsys.ip8408.feature_filmlist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [FilmEntity::class], exportSchema = false)
abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmsDao(): FilmsDao
}
