package ro.upt.ac.chiuitter.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ro.upt.ac.chiuitter.domain.Chiuit
import ro.upt.ac.chiuitter.domain.ChiuitRepository

class HomeViewModel(private val chiuitRepository: ChiuitRepository) : ViewModel() {

    val chiuitListState: Flow<List<Chiuit>> by lazy {  chiuitRepository.getAll() }


    fun addChiuit(description: String) {
       chiuitRepository.addChiuit(Chiuit(System.currentTimeMillis(), description))
    }

    fun removeChiuit(chiuit: Chiuit) {
        chiuitRepository.removeChiuit(chiuit)
    }

}