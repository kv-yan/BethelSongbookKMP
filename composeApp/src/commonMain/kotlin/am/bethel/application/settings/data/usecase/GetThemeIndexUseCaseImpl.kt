package am.bethel.application.settings.data.usecase

import am.bethel.application.common.domain.repository.SettingsRepository
import am.bethel.application.settings.domain.usecase.GetThemeIndexUseCase
import kotlinx.coroutines.flow.Flow

class GetThemeIndexUseCaseImpl(
    private val repository: SettingsRepository,
) : GetThemeIndexUseCase {
    override operator fun invoke(): Flow<Int> = repository.themeIndexFlow
}