package am.bethel.application.settings.data.usecase

import am.bethel.application.common.domain.repository.SettingsRepository
import am.bethel.application.settings.domain.usecase.GetFontSizeUseCase
import kotlinx.coroutines.flow.Flow

class GetFontSizeUseCaseImpl(
    private val repository: SettingsRepository,
) : GetFontSizeUseCase {
    override operator fun invoke(): Flow<Float> = repository.fontSizeFlow
}

