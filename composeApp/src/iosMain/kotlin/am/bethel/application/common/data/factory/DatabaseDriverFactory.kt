package am.bethel.application.common.data.factory

import am.bethel.songbook.BethelDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(BethelDatabase.Schema, "bethel.db")
    }
}
