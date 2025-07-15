package am.bethel.application.settings.domain.usecase

import kotlinx.coroutines.flow.Flow


interface GetFontSizeUseCase {
    operator fun invoke(): Flow<Float>
}

