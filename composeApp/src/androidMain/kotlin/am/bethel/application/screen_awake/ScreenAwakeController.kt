package am.bethel.application.screen_awake

import android.app.Activity
import android.view.WindowManager

actual class ScreenAwakeController(
    private val activity: Activity,
) {
    actual fun keepScreenOn(enabled: Boolean) = activity.let { activity ->
        activity.runOnUiThread {
            try {
                if (enabled) {
                    println("Keep screen on")
                    activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                } else {
                    println("Keep screen off")
                    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }
            } catch (e: Exception) {
                println("Error setting screen awake: ${e.message}")
            }
        }
    }
}