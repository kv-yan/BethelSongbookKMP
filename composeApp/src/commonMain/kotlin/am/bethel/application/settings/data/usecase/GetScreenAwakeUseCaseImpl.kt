package am.bethel.application.settings.data.usecase

import am.bethel.application.common.domain.repository.SettingsRepository
import am.bethel.application.settings.domain.usecase.GetScreenAwakeUseCase
import kotlinx.coroutines.flow.Flow

class GetScreenAwakeUseCaseImpl(
    private val repository: SettingsRepository,
) : GetScreenAwakeUseCase {
    override fun invoke(): Flow<Boolean> = repository.isScreenKeepAwakeFlow
}
