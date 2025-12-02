package am.bethel.application.screen_awake

import platform.UIKit.UIApplication

actual class ScreenAwakeController {
    actual fun keepScreenOn(enabled: Boolean) {
        UIApplication.sharedApplication.setIdleTimerDisabled(enabled)
    }
}