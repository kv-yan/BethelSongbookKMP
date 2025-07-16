package am.bethel.application.share.domain.usecase

import am.bethel.application.common.domain.model.Song

interface ShareSongUseCase {
    suspend operator fun invoke(song: Song)
}