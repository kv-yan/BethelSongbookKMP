package am.bethel.application.share.di

import am.bethel.application.share.domain.repository.ShareHelper
import am.bethel.application.share.ShareHelperIos
import org.koin.dsl.module

val shareIosModule = module {
    single<ShareHelper> {
        println("helper instance created")
        ShareHelperIos()
    }
}