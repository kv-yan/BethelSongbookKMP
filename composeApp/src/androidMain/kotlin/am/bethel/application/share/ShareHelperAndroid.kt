package am.bethel.application.share

import am.bethel.application.share.domain.repository.ShareHelper
import android.content.Context
import android.content.Intent

class ShareHelperAndroid(private val context: Context) : ShareHelper {
    override fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)  // required for non-Activity context
        }
        val chooser = Intent.createChooser(intent, "Share via").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)  // also needed on the chooser
        }
        context.applicationContext.startActivity(chooser)
    }
}