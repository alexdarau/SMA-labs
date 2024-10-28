package upt.ac.lab2.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.upt.ac.chiuitter.domain.Chiuit
import ro.upt.ac.chiuitter.presentation.HomeViewModel
import upt.ac.lab2.R
import upt.ac.lab2.data.database.ChiuitDbStore
import upt.ac.lab2.data.database.RoomDatabase
import upt.ac.lab2.presentation.ComposeActivity.Companion.EXTRA_TEXT

class MainActivity : AppCompatActivity() {

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                setChiuitText(data?.getStringExtra(EXTRA_TEXT))
            }
        }


    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = HomeViewModel(ChiuitDbStore(RoomDatabase.getDb(this)))
        setContent { HomeScreen(viewModel) }
    }

    @Composable
    private fun HomeScreen(viewModel: HomeViewModel) {
        val chiuitListState = viewModel.chiuitListState.collectAsState()
        println("Chiuuuuuuuuuuuuuuit")
        println(chiuitListState.value)
        Log.d("myTag", chiuitListState.value.toString());
        Surface(color = Color.White) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn { items(chiuitListState.value.size) { index -> ChiuitListItem(chiuitListState.value[index]) }}
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    onClick = { composeChiuit() },
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        stringResource(R.string.edit_action_icon_content_description)
                    )
                }
            }
        }
    }

    @Composable
    private fun ChiuitListItem(chiuit: Chiuit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(8.dp),
                    text = chiuit.description,
                )
                Button(
                    modifier = Modifier
                        .weight(0.2f)
                        .padding(8.dp),
                    onClick = { shareChiuit(chiuit.description) }) {
                    Icon(
                        Icons.Filled.Send,
                        stringResource(R.string.send_action_icon_content_description)
                    )
                }
            }
            // TODO 4: Add a new button that has the purpose to delete a chiuit.
        }
    }

    /*
    Defines text sharing/sending *implicit* intent, opens the application chooser menu,
    and starts a new activity which supports sharing/sending text.
     */
    private fun shareChiuit(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"

        }

        val intentChooser = Intent.createChooser(sendIntent, "")

        startActivity(intentChooser)
    }

    /*
    Defines an *explicit* intent which will be used to start ComposeActivity.
     */
    private fun composeChiuit() {
        val intent = Intent(this, ComposeActivity::class.java).apply {
            putExtra(Intent.EXTRA_TEXT, "")
            type = "text/plain"
        }

        resultLauncher.launch(intent)
    }

    private fun setChiuitText(resultText: String?) {
        if(resultText !== null) {
            // TODO 1: Instantiate a new chiuit object then delegate the addition to the [viewModel].
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun DefaultPreview() {
        HomeScreen(viewModel)
    }
}



