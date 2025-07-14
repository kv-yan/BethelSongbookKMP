package am.bethel.application.common.data.factory

import am.bethel.songbook.BethelDatabase
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(BethelDatabase.Schema, context, "bethel.db")
    }
}
