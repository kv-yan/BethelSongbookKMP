package am.bethel.application.settings.domain.usecase

interface ChangeFontSizeUseCase {
    suspend operator fun invoke(newSize: Float)
}