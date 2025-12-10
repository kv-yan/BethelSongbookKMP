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
                    activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                } else {
                    activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }
            } catch (e: Exception) {
                println("Error setting screen awake: ${e.message}")
            }
        }
    }
}