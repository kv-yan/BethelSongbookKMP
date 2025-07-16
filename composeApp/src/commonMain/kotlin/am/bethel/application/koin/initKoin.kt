package am.bethel.application.koin

import org.koin.core.KoinApplication
import org.koin.core.module.Module

expect fun initKoin(vararg modules: Module, appDeclaration: KoinApplication.() -> Unit = {})
