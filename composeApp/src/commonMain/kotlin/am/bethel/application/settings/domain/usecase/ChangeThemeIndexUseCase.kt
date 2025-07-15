package am.bethel.application.settings.domain.usecase

interface ChangeThemeIndexUseCase {
    suspend operator fun invoke(index: Int)
}