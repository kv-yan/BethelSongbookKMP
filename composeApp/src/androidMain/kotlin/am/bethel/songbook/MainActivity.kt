package am.bethel.songbook

import am.bethel.application.navigation.navigation_component.RootComponent
import am.bethel.application.screen_awake.ScreenAwakeController
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalDecomposeApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = retainedComponent { RootComponent(it) }
        val screenAwakeController = ScreenAwakeController(this)
        val isSplashLoaded = MutableStateFlow(false)

        val updateManager = AppUpdateManagerFactory.create(this)

        updateManager.appUpdateInfo.addOnSuccessListener { info ->
            // Immediate update
            if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                info.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                val options = AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()

                updateManager.startUpdateFlowForResult(
                    info,
                    this,
                    options,
                    1001
                )
            }

            // Flexible update
            if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                info.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                val options = AppUpdateOptions.newBuilder(AppUpdateType.FLEXIBLE).build()

                updateManager.startUpdateFlowForResult(
                    info,
                    this,
                    options,
                    1002
                )
            }
        }

        setContent {
            LaunchedEffect(Unit) {
                delay(1000)
                isSplashLoaded.value = true
            }

            val changeStatusBarColor: (isDarkIcons: Boolean) -> Unit = { isDarkIcons ->
                enableEdgeToEdge(
                    statusBarStyle = if (!isDarkIcons) {
                        SystemBarStyle.dark(Color.Transparent.hashCode()) // черные иконки
                    } else {
                        SystemBarStyle.light(
                            Color.Transparent.hashCode(), // белые иконки
                            Color.White.hashCode() // fallback иконки на старых API
                        )
                    }, navigationBarStyle = if (isDarkIcons) {
                        SystemBarStyle.dark(Color.Transparent.hashCode())
                    } else {
                        SystemBarStyle.light(
                            Color.Transparent.hashCode(), Color.White.hashCode()
                        )
                    }
                )
            }

            App(
                root = root,
                onSystemBarColorChange = changeStatusBarColor,
                keepScreenOn = {
                    screenAwakeController.keepScreenOn(it)
                }
            )
        }

        // Set up an OnPreDrawListener to the root view.
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check whether the initial data is ready.
                    return if (isSplashLoaded.value) {
                        // The content is ready. Start drawing.
                        application.setTheme(R.style.Theme_Songbook)
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content isn't ready. Suspend.
                        false
                    }
                }
            }
        )
    }
}
