package am.bethel.songbook

import am.bethel.application.common.data.factory.di.androidModule
import am.bethel.application.koin.initKoin
import am.bethel.application.share.di.shareAndroidModule
import android.app.Application
import org.koin.android.ext.koin.androidContext

class SongbookApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            androidModule,
            shareAndroidModule
        ) {
            androidContext(this@SongbookApp)
        }
    }
}