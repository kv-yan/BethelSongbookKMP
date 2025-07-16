package am.bethel.application.share


import am.bethel.application.share.domain.repository.ShareHelper
import platform.UIKit.*

class ShareHelperIos : ShareHelper {
    override fun shareText(text: String) {
        val activityItems = listOf(text)
        val activityViewController = UIActivityViewController(activityItems, null)

        val application = UIApplication.sharedApplication
        application.keyWindow?.rootViewController?.presentViewController(
            activityViewController,
            animated = true,
            completion = null
        )
    }
}
