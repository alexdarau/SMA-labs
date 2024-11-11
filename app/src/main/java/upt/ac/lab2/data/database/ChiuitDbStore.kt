package upt.ac.lab2.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ro.upt.ac.chiuitter.domain.Chiuit
import ro.upt.ac.chiuitter.domain.ChiuitRepository

class ChiuitDbStore(private val appDatabase: AppDatabase) : ChiuitRepository {

    override fun getAll(): Flow<List<Chiuit>> {
        return appDatabase.chiuitDao().getAll().map { list -> list.map { it.toDomainModel() } }
    }

    override fun addChiuit(chiuit: Chiuit) {
        return appDatabase.chiuitDao().insert(chiuit.toDbModel())
    }

    override fun removeChiuit(chiuit: Chiuit) {
        return appDatabase.chiuitDao().delete(chiuit.toDbModel())
    }


    private fun Chiuit.toDbModel() = ChiuitEntity(timestamp, description)

    private fun ChiuitEntity.toDomainModel() = Chiuit(timestamp, description)

}