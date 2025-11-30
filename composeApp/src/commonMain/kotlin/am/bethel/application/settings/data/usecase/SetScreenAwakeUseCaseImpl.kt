package am.bethel.application.settings.data.usecase

import am.bethel.application.common.domain.repository.SettingsRepository
import am.bethel.application.settings.domain.usecase.SetScreenAwakeUseCase

class SetScreenAwakeUseCaseImpl(
    private val repository: SettingsRepository,
) : SetScreenAwakeUseCase {
    override suspend fun invoke(boolean: Boolean) {
        repository.setScreenKeepAwake(boolean)
    }
}