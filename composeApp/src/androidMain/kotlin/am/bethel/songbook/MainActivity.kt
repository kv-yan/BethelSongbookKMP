package am.bethel.songbook

import am.bethel.application.common.data.factory.di.androidModule
import am.bethel.application.koin.initKoin
import am.bethel.application.navigation.navigation_component.RootComponent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import org.koin.android.ext.koin.androidContext

@OptIn(ExperimentalDecomposeApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = retainedComponent { RootComponent(it) }
        initKoin(androidModule) {
            androidContext(applicationContext)
        }
        setContent {
            val changeStatusBarColor: (isDarkIcons: Boolean) -> Unit = { isDarkIcons ->
                println("isDarkIcons: $isDarkIcons")
                enableEdgeToEdge(
                    statusBarStyle = if (!isDarkIcons) {
                        println("status bar color is dark")
                        SystemBarStyle.dark(Color.Transparent.hashCode()) // черные иконки
                    } else {
                        println("status bar color is light")
                        SystemBarStyle.light(
                            Color.Transparent.hashCode(), // белые иконки
                            Color.White.hashCode() // fallback иконки на старых API
                        )
                    },
                    navigationBarStyle = if (isDarkIcons) {
                        println("navigation bar color is dark")
                        SystemBarStyle.dark(Color.Transparent.hashCode())
                    } else {
                        println("navigation bar color is light")
                        SystemBarStyle.light(
                            Color.Transparent.hashCode(),
                            Color.White.hashCode()
                        )
                    }
                )
            }
            App(
                root = root,
                onSystemBarColorChange = changeStatusBarColor
            )
        }
    }
}
