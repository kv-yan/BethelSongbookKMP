package am.bethel.application.share

import am.bethel.application.share.domain.repository.ShareHelper
import android.content.Context
import android.content.Intent

class ShareHelperAndroid(private val context: Context) : ShareHelper {
    override fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        val chooser = Intent.createChooser(intent, "Share via")
        context.startActivity(chooser)
    }
}
