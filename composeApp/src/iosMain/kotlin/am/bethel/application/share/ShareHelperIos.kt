package am.bethel.application.share


import am.bethel.application.share.domain.repository.ShareHelper
import platform.UIKit.*
import platform.UIKit.UIApplication
import platform.UIKit.UIActivityViewController
import platform.darwin.*

class ShareHelperIos : ShareHelper {
    override fun shareText(text: String) {
        dispatch_async(dispatch_get_main_queue()) {
            try {
                val activityVC = UIActivityViewController(
                    activityItems = listOf(text),
                    applicationActivities = null
                )

                val keyWindow = UIApplication.sharedApplication.windows.first() as? UIWindow
                val rootVC = keyWindow?.rootViewController
                rootVC?.presentViewController(activityVC, animated = true, completion = null)
            } catch (e: Exception) {
                println("iOS share error: ${e.message}")
            }
        }
    }
}
