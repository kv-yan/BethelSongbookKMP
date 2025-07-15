package am.bethel.application.settings.data.usecase

import am.bethel.application.common.domain.repository.SettingsRepository
import am.bethel.application.settings.domain.usecase.ChangeFontSizeUseCase


class ChangeFontSizeUseCaseImpl(
    private val repository: SettingsRepository,
) : ChangeFontSizeUseCase {
    override suspend operator fun invoke(newSize: Float) {
        repository.setFontSize(newSize)
    }
}

