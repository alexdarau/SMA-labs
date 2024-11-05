package upt.ac.lab2.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ro.upt.ac.chiuitter.domain.Chiuit


@Dao
interface ChiuitDao {

    @Query("SELECT * FROM chiuits")
    fun getAll(): List<ChiuitEntity>

    // TODO 3: Define the insert operation.
    @Insert
    fun insert(chiuit: ChiuitEntity)

    // TODO 6: Define the delete operation.
    @Delete
    fun delete(chiuit: ChiuitEntity)

}