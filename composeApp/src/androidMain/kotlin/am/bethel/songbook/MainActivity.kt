package am.bethel.songbook

import am.bethel.application.common.data.factory.di.androidModule
import am.bethel.application.koin.initKoin
import am.bethel.application.navigation.navigation_component.RootComponent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent

@OptIn(ExperimentalDecomposeApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val root = retainedComponent {
            RootComponent(it)
        }
        initKoin(
            androidModule,
        )
        setContent {
            App(root)
        }
    }
}
