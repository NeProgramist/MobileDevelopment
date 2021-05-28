package ua.kpi.comsys.ip8408.feature_imagelist.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImagesDao {
    @Query("select * from images")
    fun getImages(): List<ImagesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: ImagesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(images: List<ImagesEntity>)
}
