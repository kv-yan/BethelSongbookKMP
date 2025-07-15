package am.bethel.application.settings.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetThemeIndexUseCase {
    operator fun invoke(): Flow<Int>
}