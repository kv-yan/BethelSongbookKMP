package am.bethel.application.settings.domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetScreenAwakeUseCase {
    operator fun invoke(): Flow<Boolean>
}

