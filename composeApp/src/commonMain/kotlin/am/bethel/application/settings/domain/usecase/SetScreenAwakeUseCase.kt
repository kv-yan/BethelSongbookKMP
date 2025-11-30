package am.bethel.application.settings.domain.usecase

interface SetScreenAwakeUseCase {
    suspend operator fun invoke(boolean: Boolean)
}
