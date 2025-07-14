package am.bethel.application.common.data.di

import am.bethel.application.common.data.factory.DatabaseDriverFactory
import am.bethel.application.common.data.repository.SongRepositoryImpl
import am.bethel.application.common.domain.repository.SongRepository
import am.bethel.songbook.BethelDatabase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonDataModule= module{
    singleOf(::SongRepositoryImpl){bind<SongRepository>()}

    single<BethelDatabase> {
        BethelDatabase(
            driver = get<DatabaseDriverFactory>().createDriver()
        )
    }

}