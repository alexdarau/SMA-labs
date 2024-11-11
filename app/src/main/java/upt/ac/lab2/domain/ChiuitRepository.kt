package ro.upt.ac.chiuitter.domain

import kotlinx.coroutines.flow.Flow

interface ChiuitRepository {

    fun getAll(): Flow<List<Chiuit>>

    fun addChiuit(chiuit: Chiuit)

    fun removeChiuit(chiuit: Chiuit)
}