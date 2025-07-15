package am.bethel.application.settings.data.usecase

import am.bethel.application.common.domain.repository.SettingsRepository
import am.bethel.application.settings.domain.usecase.ChangeThemeIndexUseCase

class ChangeThemeIndexUseCaseImpl(
    private val repository: SettingsRepository,
) : ChangeThemeIndexUseCase {

    override suspend fun invoke(index: Int) {
        repository.setThemeIndex(index)
    }
}
