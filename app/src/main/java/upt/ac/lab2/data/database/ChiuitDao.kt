package upt.ac.lab2.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ro.upt.ac.chiuitter.domain.Chiuit


@Dao
interface ChiuitDao {

    @Query("SELECT * FROM chiuits")
    fun getAll(): Flow<List<ChiuitEntity>>

    @Insert
    fun insert(chiuit: ChiuitEntity)

    @Delete
    fun delete(chiuit: ChiuitEntity)

}