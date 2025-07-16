package am.bethel.application.common.data.helper

import android.content.Context
import org.koin.core.context.GlobalContext

actual suspend fun readSongsJson(): String {
    val context = GlobalContext.get().get<Context>() // Koin provides the Context
    return context.assets.open("songs.json").bufferedReader().use { it.readText() }
}