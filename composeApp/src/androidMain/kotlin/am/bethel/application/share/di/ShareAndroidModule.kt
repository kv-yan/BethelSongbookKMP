package am.bethel.application.share.di

import am.bethel.application.share.domain.repository.ShareHelper
import am.bethel.application.share.ShareHelperAndroid
import org.koin.dsl.module

val shareAndroidModule = module {
    single<ShareHelper> { ShareHelperAndroid(get()) }
}